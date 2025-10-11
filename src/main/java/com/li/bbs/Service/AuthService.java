package com.li.bbs.Service;

import com.li.bbs.Pojo.LoginRequest;
import com.li.bbs.Pojo.User;

public interface AuthService {
    public void register (User newUser);
    public String login (LoginRequest LoginUser);

    void updateTime(Integer userId);

    void logout(User newUserInfo, String token);
}
