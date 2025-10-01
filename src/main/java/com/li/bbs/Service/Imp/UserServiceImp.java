package com.li.bbs.Service.Imp;


import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.li.bbs.Exception.NoResourceFoundException;
import com.li.bbs.Mapper.UserMapper;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.UserService;
import com.li.bbs.util.JwtUtil;
import com.li.bbs.util.OssUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    OssUtil ossUtil;

    @Override
    public UserResponse getInfo(String token) {
        Integer userId = jwtUtil.extractUserId(token);
        UserResponse userInfo = userMapper.findById(userId);
        if (userInfo == null){
            throw new NoResourceFoundException("用户不存在");
        }
        return userInfo;
    }

    @Override
    public PageResult<Post> getFavourites(String token, QueryParam queryParam) {
        Integer userId = jwtUtil.extractUserId(token);
        Page<Post> p = PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        List<Post> allFavourites = userMapper.findAllFavourites(userId);
        return new PageResult<>(p.getTotal(), allFavourites);
    }

    @Override
    public void addFavourite(String token, Integer postId) {
        Integer userId = jwtUtil.extractUserId(token);
        Integer res = userMapper.addFavourite(userId, postId, LocalDateTime.now());
        if (res != 1){
            throw new RuntimeException("添加失败");
        }
    }

    @Override
    public void removeFavourite(String token, Integer postId) {
        Integer userId = jwtUtil.extractUserId(token);
        Integer res = userMapper.removeFavourite(userId, postId);
        if (res != 1){
            throw new RuntimeException("删除失败");
        }

    }

    @Override
    public PageResult<Post> getMyPosts(String token, QueryParam queryParam) {
        Integer userId = jwtUtil.extractUserId(token);
        Page<Post> p = PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        List<Post> myPosts = userMapper.findPostsByUserId(userId);
        return new PageResult<>(p.getTotal(), myPosts);

    }

    @SneakyThrows
    @Override
    public void updateUserInfo(String token, User user) {
        user.setUpdatedTime(LocalDateTime.now());
        Integer userId = jwtUtil.extractUserId(token);
        Integer res = userMapper.updateUserInfo(userId, user);
        if (res != 1){
            throw new RuntimeException("更新信息失败");
        }
    }

    @Override
    public String updateUserAvatar(String token, MultipartFile file) throws ClientException {
        String avatarUrl = ossUtil.uploadFile(file);
        Integer res = userMapper.updateUserAvatar(jwtUtil.extractUserId(token), avatarUrl);
        if (res != 1){
            throw new RuntimeException("更新头像失败");
        }
        return avatarUrl;
    }


}
