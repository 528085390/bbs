package com.li.bbs.Service.Imp;

import com.li.bbs.Mapper.AuthMapper;
import com.li.bbs.Mapper.UserMapper;
import com.li.bbs.Pojo.User;
import com.li.bbs.Pojo.UserResponse;
import com.li.bbs.Service.AuthService;
import com.li.bbs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDateTime;


@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthMapper authMapper;

    @Override
    public void register(User newUserInfo) {
        if (authMapper.findByUsername(newUserInfo.getUsername()) != null){
            throw new RuntimeException("用户名已存在");
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(newUserInfo.getPassword());
        newUserInfo.setPassword(encodedPassword);
        newUserInfo.setCreatedTime(LocalDateTime.now());
        newUserInfo.setUpdatedTime(LocalDateTime.now());
        authMapper.addUser(newUserInfo);
    }

    @Transactional()
    @Override
    public String login(String username, String password) {
        User user = authMapper.findByUsername(username);
        if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            return jwtUtil.generateToken(username, user.getId());
        }
        throw new RuntimeException("用户名或密码错误");
    }


    @Override
    public void updateTime(Integer userId) {
        authMapper.updateTime(LocalDateTime.now(), userId);
    }


}
