package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.AuthController;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Pojo.User;
import com.li.bbs.Service.AuthService;
import com.li.bbs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.li.bbs.Pojo.Result.success;


@RequestMapping("/auth")
@RestController
public class AuthControllerImp implements AuthController {

    @Autowired
    private AuthService authService;



    @PostMapping("/register")
    @Override
    public Result<Integer> register(@RequestBody User newUserInfo) {
        Integer res = authService.register(newUserInfo);
        if (res != 1){
            return Result.error(Result.ERROR,"注册失败");
        }
        return Result.success();
    }

    @PostMapping("/login")
    @Override
    public Result<String> login(@RequestBody User longinUser) {
        if (longinUser.getUsername() == null || longinUser.getPassword() == null){
            return Result.error(Result.PARAM_ERROR,"参数错误");
        }
        String token = authService.login(longinUser.getUsername(),longinUser.getUsername());
        if (token == null) {
            return Result.error(Result.UNAUTHORIZED,"用户名或密码错误");
        }
        return Result.success(token);
    }
}
