package com.li.bbs.Service.Imp;

import com.li.bbs.Mapper.AuthMapper;
import com.li.bbs.Pojo.User;
import com.li.bbs.Service.AuthService;
import com.li.bbs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthMapper authMapper;

    @Override
    public Integer register(User newUserInfo) {
        newUserInfo.setCreatedTime(LocalDateTime.now());
        newUserInfo.setUpdatedTime(LocalDateTime.now());
        Integer res = authMapper.register(newUserInfo);
        return res;
    }

    @Override
    public String login(String username, String password) {
        User user = authMapper.login(username, password);
        if (user == null){
            return null;
        }
        return jwtUtil.generateToken(username, user.getId());



    }
}
