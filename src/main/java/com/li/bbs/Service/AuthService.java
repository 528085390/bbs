package com.li.bbs.Service;

import com.li.bbs.Pojo.User;
import com.li.bbs.Pojo.UserResponse;

import java.time.LocalDateTime;

public interface AuthService {
    public void register (User newUser);
    public String login (String username, String password);

    void updateTime(Integer userId);

}
