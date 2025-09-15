package com.li.bbs.Controller;

import com.li.bbs.Pojo.Result;
import com.li.bbs.Pojo.User;

public interface AuthController {

    public Result<Integer> register(User newUserInfo);
    public Result<String> login(String username, String password);
}
