package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.UserController;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


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
    public Result<PageResult<Post>> getFavourites(@RequestHeader String token,@RequestBody QueryParam queryParam) {
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

    @GetMapping("/posts")
    @Override
    public Result<PageResult<Post>> getMyPosts(@RequestHeader String token,@RequestBody QueryParam queryParam) {
        PageResult<Post> myPosts = userService.getMyPosts(token, queryParam);
        return Result.success(myPosts);

    }

    @SneakyThrows
    @PostMapping("/update")
    @Override
    public Result updateUser(@RequestHeader String token,@RequestBody User user,@RequestBody MultipartFile file) {
        userService.updateUser(token, user, file);
        return Result.success();
    }


}
