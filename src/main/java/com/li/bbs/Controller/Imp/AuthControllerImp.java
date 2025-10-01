package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.AuthController;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Pojo.User;
import com.li.bbs.Pojo.UserResponse;
import com.li.bbs.Service.AuthService;
import com.li.bbs.util.JwtUtil;
import jakarta.validation.Valid;
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
    public Result register(@Valid @RequestBody User newUserInfo) {
        authService.register(newUserInfo);
        return Result.success();
    }

    @PostMapping("/login")
    @Override
    public Result<String> login(@RequestBody User longinUser) {
        String token = authService.login(longinUser.getUsername(),longinUser.getPassword());
        return Result.success(token);
    }

}
