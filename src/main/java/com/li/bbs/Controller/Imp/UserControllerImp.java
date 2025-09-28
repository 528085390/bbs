package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.UserController;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserControllerImp implements UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public Result<UserResponse> getInfo(@RequestHeader String token) {
        UserResponse userInfo = userService.getInfo(token);
        return Result.success(userInfo);
    }

    @GetMapping("/favourites")
    @Override
    public Result<PageResult<Post>> getFavourites(@RequestHeader String token, QueryParam queryParam) {
        PageResult<Post> favourites = userService.getFavourites(token,queryParam);
        return Result.success(favourites);
    }

    @PostMapping("/favourite")
    @Override
    public Result addFavourite(@RequestHeader String token, Integer postId) {
        userService.addFavourite(token, postId);
        return Result.success();
    }

    @DeleteMapping("/favourite")
    @Override
    public Result removeFavourite(@RequestHeader String token, Integer postId) {
        userService.removeFavourite(token, postId);
        return Result.success();
    }


}
