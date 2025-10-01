package com.li.bbs.Service.Imp;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.li.bbs.Mapper.UserMapper;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;
import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.UserResponse;
import com.li.bbs.Service.UserService;
import com.li.bbs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public UserResponse getInfo(String token) {
        Integer userId = jwtUtil.extractUserId(token);
        UserResponse userInfo  = userMapper.findById(userId);
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
        userMapper.addFavourite(userId,postId, LocalDateTime.now());
    }

    @Override
    public void removeFavourite(String token, Integer postId) {
        Integer userId = jwtUtil.extractUserId(token);
        userMapper.removeFavourite(userId,postId);

    }

    @Override
    public PageResult<Post> getMyPosts(String token, QueryParam queryParam) {
        return null;
    }


}
