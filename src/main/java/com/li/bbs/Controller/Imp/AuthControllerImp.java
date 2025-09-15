package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.AuthController;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Pojo.User;
import com.li.bbs.Service.AuthService;
import com.li.bbs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.li.bbs.Pojo.Result.success;


@RequestMapping("auth")
@RestController
public class AuthControllerImp implements AuthController {

    @Autowired
    private AuthService authService;



    @PostMapping("register")
    @Override
    public Result<Integer> register(User newUserInfo) {
        Integer newUser = authService.register(newUserInfo);
        return Result.success(newUser);
    }

    @PostMapping("login")
    @Override
    public Result<String> login(String username, String password) {
        if (username == null || password == null){
            return Result.error(Result.PARAM_ERROR,"参数错误");
        }
        String token = authService.login(username,password);
        if (token == null) {
            return Result.error(Result.UNAUTHORIZED,"用户名或密码错误");
        }
        return Result.success(token);
    }
}
