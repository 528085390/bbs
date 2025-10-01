package com.li.bbs.Service;

import com.li.bbs.Pojo.LoginRequest;
import com.li.bbs.Pojo.User;
import com.li.bbs.Pojo.UserResponse;

import java.time.LocalDateTime;

public interface AuthService {
    public void register (User newUser);
    public String login (LoginRequest LoginUser);

    void updateTime(Integer userId);

}
