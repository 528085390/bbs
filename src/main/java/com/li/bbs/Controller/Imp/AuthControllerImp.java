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
    AuthService authService;

    @Autowired
    ValidationUtil validationUtil;


    /**
     * 用户注册
     * @param newUserInfo 用户注册信息
     */
    @PostMapping("/register")
    @Override
    public Result register(@RequestBody User newUserInfo) {
        log.info("正在进行用户注册...");
        if (!validationUtil.isValidUsername(newUserInfo.getUsername())){
            return Result.error(Result.PARAM_ERROR,"用户名不符合要求：不能为空、用户名不能含有中文、长度必须在2到20之间...");
        }
        if(!validationUtil.isValidPassword(newUserInfo.getPassword())){
            return Result.error(Result.PARAM_ERROR,"密码不符合要求：不能为空、长度必须在6到20之间...");
        }
        if(!validationUtil.isValidEmail(newUserInfo.getEmail())){
            return Result.error(Result.PARAM_ERROR,"用户邮箱格式不符合要求...");
        }
        authService.register(newUserInfo);
        log.info("用户({})注册成功",newUserInfo.getUsername());
        return Result.success(CREATED);
    }
    /**
     * 用户登录
     * @param user 用户登录信息
     */

    @PostMapping("/login")
    @Override
    public Result<String> login(@RequestBody LoginRequest user) {
        log.info("正在进行用户登录...");
        if (!validationUtil.isValidUsername(user.getUsername())){
            return Result.error(Result.PARAM_ERROR,"用户名不符合要求：不能为空、用户名不能含有中文、长度必须在2到20之间...");
        }
        if(!validationUtil.isValidPassword(user.getPassword())){
            return Result.error(Result.PARAM_ERROR,"密码不符合要求：不能为空、长度必须在6到20之间...");
        }
        String token = authService.login(user);
        log.info("用户({})登录成功",user.getUsername());
        return Result.success(token);
    }


    /**
     * 用户登出
     */

    @PostMapping("/logout")
    @Override
    public Result logout(@RequestHeader String token) {
        log.info("正在进行用户登出...");
        if (token==null|| token.isEmpty()){
            return Result.error(FORBIDDEN,"用户未登录...");
        }
        authService.logout(token);
        log.info("已经成功登出");
        return Result.success(SUCCESS);

    }

}
