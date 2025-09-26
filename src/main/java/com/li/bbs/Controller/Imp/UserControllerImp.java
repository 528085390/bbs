package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.UserController;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Pojo.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserControllerImp implements UserController {


    @GetMapping("/me")
    public Result<User> findById(String token) {
        System.out.printf(token);

        return null;
    }
}
