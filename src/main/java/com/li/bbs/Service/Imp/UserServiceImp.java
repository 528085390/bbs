package com.li.bbs.Service.Imp;


import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.li.bbs.Exception.NoResourceFoundException;
import com.li.bbs.Exception.ResourceDuplicateException;
import com.li.bbs.Mapper.UserMapper;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.UserService;
import com.li.bbs.util.JwtUtil;
import com.li.bbs.util.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        Integer isFavourite = userMapper.isFavourite(userId, postId);
        if (isFavourite == 1){
            throw new ResourceDuplicateException("该帖子已收藏");
        }
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
    public boolean isFavourite(String token, Integer postId){
         Integer userId = jwtUtil.extractUserId(token);
         Integer count = userMapper.isFavourite(userId, postId);
         return count !=null && count > 0;
    }

    @Override
    public boolean isMyPost(String token, Integer postId) {
        Integer userId = jwtUtil.extractUserId(token);
        Integer count = userMapper.isMyPost(userId, postId);
        return (count == 1);
    }

    @Override
    public PageResult<Post> getMyPosts(String token, QueryParam queryParam) {
        Integer userId = jwtUtil.extractUserId(token);
        Page<Post> p = PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        List<Post> myPosts = userMapper.findPostsByUserId(userId);
        return new PageResult<>(p.getTotal(), myPosts);

    }


    @Override
    public void updateUserInfo(String token, User user) {
        Integer userId = jwtUtil.extractUserId(token);
        UserResponse oldUser = userMapper.findById(userId);
        if (!oldUser.getEmail().equals(user.getEmail()) && userMapper.findByEmail(user.getEmail()) != null){
            throw new ResourceDuplicateException("邮箱已存在");
        }
        if (!oldUser.getUsername().equals(user.getUsername()) && userMapper.findByUsername(user.getUsername()) != null){
            throw new ResourceDuplicateException("用户名已存在");
        }
        user.setUpdatedTime(LocalDateTime.now());
        user.setId(userId);
       userMapper.updateUserInfo(user);
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

    @Override
    public void updatePassword(User newUserPassword,String token){
        User existingUserEmail=userMapper.findByEmail(newUserPassword.getEmail());
        Integer userId = jwtUtil.extractUserId(token);
        if (existingUserEmail==null){
            throw new NoResourceFoundException("用户邮箱错误");
        }
        if (!userId.equals(existingUserEmail.getId())){
            throw new BadCredentialsException("用户权限错误");
        }
        String oldPassword = existingUserEmail.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(newUserPassword.getPassword());
        newUserPassword.setId(userId);
        newUserPassword.setPassword(encodedPassword);
        newUserPassword.setUpdatedTime(LocalDateTime.now());
        if(new BCryptPasswordEncoder().matches(oldPassword,encodedPassword)){
            throw new RuntimeException("新密码不能与旧密码相同");
        }
        userMapper.updatePassword(newUserPassword);

    }

}
