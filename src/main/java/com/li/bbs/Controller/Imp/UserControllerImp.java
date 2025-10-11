package com.li.bbs.Controller.Imp;

import com.aliyuncs.exceptions.ClientException;
import com.li.bbs.Controller.UserController;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.UserService;
import com.li.bbs.util.ValidationUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.li.bbs.Pojo.Result.SUCCESS;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserControllerImp implements UserController {

    @Autowired
    private UserService userService;

    @Autowired
     ValidationUtil validationUtil;

    @GetMapping("/me")
    @Override
    public Result<UserResponse> getInfo(@RequestHeader String token) {
        UserResponse userInfo = userService.getInfo(token);
        log.info("获取用户信息：{}",userInfo);
        return Result.success(userInfo);
    }

    @GetMapping("/favourites")
    @Override
    public Result<PageResult<Post>> getFavourites(@RequestHeader String token,QueryParam queryParam) {

        PageResult<Post> favourites = userService.getFavourites(token,queryParam);
        log.info("获取用户收藏：{}",favourites);
        return Result.success(favourites);
    }

    @PostMapping("/favourite")
    @Override
    public Result addFavourite(@RequestHeader String token, Integer postId) {
        userService.addFavourite(token, postId);
        //校验postId参数
        if(postId==null||postId<=0){
            throw new IllegalArgumentException("添加的收藏帖子Id无效...");
        }
        log.info("添加收藏成功");
        return Result.success(Result.CREATED);
    }

    @DeleteMapping("/favourite")
    @Override
    public Result removeFavourite(@RequestHeader String token, Integer postId) {
        userService.removeFavourite(token, postId);
        // 校验postId参数
        if (postId == null || postId <= 0) {
            return Result.error(Result.PARAM_ERROR, "删除的帖子ID无效");
        }
        log.info("删除收藏成功");
        return Result.success(Result.NO_CONTENT);
    }

    @GetMapping("/posts")
    @Override
    public Result<PageResult<Post>> getMyPosts(@RequestHeader String token,QueryParam queryParam) {
        PageResult<Post> myPosts = userService.getMyPosts(token, queryParam);
        log.info("获取用户帖子：{}",myPosts);
        return Result.success(myPosts);

    }

    @SneakyThrows
    @PostMapping("/updateInfo")
    @Override
    public Result updateUserInfo(@RequestHeader String token,@RequestBody User user) {
        if(!validationUtil.isValidEmail(user.getEmail())){
            return Result.error(Result.PARAM_ERROR,"用户邮箱格式不符合要求...");
        }
        userService.updateUserInfo(token, user);

        log.info("更新用户信息成功");
        return Result.success(Result.NO_CONTENT);
    }

    @PostMapping("/updateAvatar")
    @Override
    public Result<String> updateUserAvatar(@RequestHeader String token, MultipartFile file) throws ClientException {
        String url = userService.updateUserAvatar(token, file);
        log.info("更新用户头像成功：{}",url);
        return Result.success(url);
    }

    @PostMapping("/updatepassword")
    @Override
    public Result updatepassword(@RequestBody User newUserInfo) {
        if(!validationUtil.isValidEmail(newUserInfo.getEmail())){
            return Result.error(Result.PARAM_ERROR,"用户邮箱格式不符合要求...");
        }
        userService.updatePassword(newUserInfo);
        log.info("用户修改密码成功");
        return Result.success(SUCCESS);
    }

}
