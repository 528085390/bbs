package com.li.bbs.Service.Imp;

import com.li.bbs.Mapper.AuthMapper;
import com.li.bbs.Pojo.User;
import com.li.bbs.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class AuthServiceImp implements AuthService {


    @Autowired
    private AuthMapper authMapper;

    @Override
    public Integer register(User newUserInfo) {
        newUserInfo.setCreatedTime(LocalDateTime.now());
        newUserInfo.setUpdatedTime(LocalDateTime.now());
        Integer newUser = authMapper.register(newUserInfo);
        return newUser;
    }
}
