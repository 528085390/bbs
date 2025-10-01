package com.li.bbs.Controller.Imp;

import com.aliyuncs.exceptions.ClientException;
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
    @PostMapping("/updateInfo")
    @Override
    public Result updateUserInfo(@RequestHeader String token,@RequestBody User user) {
        userService.updateUserInfo(token, user);
        return Result.success();
    }

    @PostMapping("/updateAvatar")
    @Override
    public Result<String> updateUserAvatar(@RequestHeader String token, MultipartFile file) throws ClientException {
        String url = userService.updateUserAvatar(token, file);
        return Result.success(url);
    }


}
