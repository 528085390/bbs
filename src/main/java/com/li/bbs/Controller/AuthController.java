package com.li.bbs.Controller;

import com.li.bbs.Pojo.LoginRequest;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Pojo.User;
import com.li.bbs.Pojo.UserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {

    public Result<Integer> register(User newUserInfo);
    public Result<String> login(LoginRequest loginUser);

    public Result logout(User newUserInfo);
    public Result updatepassword(User newUserInfo);
}
