package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.AuthController;
import com.li.bbs.Pojo.LoginRequest;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Pojo.User;
import com.li.bbs.Service.AuthService;
import com.li.bbs.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.li.bbs.Pojo.Result.*;


@RequestMapping("/auth")
@RestController
@Slf4j
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
        log.info("用户注册成功");
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
        log.info("用户登录成功");
        return Result.success(token);
    }

    @PostMapping("/logout")
    @Override
    public Result logout(@RequestHeader String token) {
        if (token==null|| token.isEmpty()){
            return Result.error(FORBIDDEN,"用户未登录...");
        }
        authService.logout(token);
        log.info("用户成功登出");
        return Result.success(SUCCESS);

    }

}
