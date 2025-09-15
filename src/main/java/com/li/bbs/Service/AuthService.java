package com.li.bbs.Service;

import com.li.bbs.Pojo.User;

public interface AuthService {
    public Integer register (User newUser);
    public String login (String username, String password);
}
