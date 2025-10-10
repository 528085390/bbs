package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.AuthController;
import com.li.bbs.Pojo.LoginRequest;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Pojo.User;
import com.li.bbs.Pojo.UserResponse;
import com.li.bbs.Service.AuthService;
import com.li.bbs.util.JwtUtil;
import com.li.bbs.util.ValidationUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.li.bbs.Pojo.Result.CREATED;
import static com.li.bbs.Pojo.Result.success;


@RequestMapping("/auth")
@RestController
public class AuthControllerImp implements AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    ValidationUtil validationUtil;

    @PostMapping("/register")
    @Override
    public Result register(@RequestBody User newUserInfo) {
        if (!validationUtil.isValidUsername(newUserInfo.getUsername())){
            return Result.error(Result.PARAM_ERROR,"用户名不符合要求：不能为空、用户名不能含有中文、长度必须在2到20之间...");
        }
        if(!validationUtil.isValidPassword(newUserInfo.getPassword())){
            return Result.error(Result.PARAM_ERROR,"密码不符合要求：不能为空、长度必须在6到20之间...");
        }
        authService.register(newUserInfo);
        return Result.success(CREATED);
    }

    @PostMapping("/login")
    @Override
    public Result<String> login(@RequestBody LoginRequest user) {
        if (!validationUtil.isValidUsername(user.getUsername())){
            return Result.error(Result.PARAM_ERROR,"用户名不符合要求：不能为空、用户名不能含有中文、长度必须在2到20之间...");
        }
        if(!validationUtil.isValidPassword(user.getPassword())){
            return Result.error(Result.PARAM_ERROR,"密码不符合要求：不能为空、长度必须在6到20之间...");
        }
        String token = authService.login(user);
        return Result.success(token);
    }

}
