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

import static com.li.bbs.Pojo.Result.NO_CONTENT;
import static com.li.bbs.Pojo.Result.SUCCESS;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserControllerImp implements UserController {

    @Autowired
    UserService userService;

    @Autowired
     ValidationUtil validationUtil;

    @GetMapping("/me")
    @Override
    public Result<UserResponse> getInfo(@RequestHeader String token) {
        log.info("正在获取用户信息...");
        UserResponse userInfo = userService.getInfo(token);
        log.info("获取用户信息：{}",userInfo);
        return Result.success(userInfo);
    }

    @GetMapping("/favourites")
    @Override
    public Result<PageResult<Post>> getFavourites(@RequestHeader String token,QueryParam queryParam) {
        log.info("正在获取用户收藏...");
        PageResult<Post> favourites = userService.getFavourites(token,queryParam);
        log.info("获取用户收藏：{}",favourites);
        return Result.success(favourites);
    }
    @GetMapping("/checkFavourite")
    @Override
    public Result<Boolean> isFavourite(@RequestHeader String token,@RequestParam Integer postId) {
        log.info("正在判断用户是否收藏帖子，帖子Id：{}",postId);
        //校验postId参数
        if(postId==null||postId<=0){
            throw new IllegalArgumentException("添加的收藏帖子Id无效...");
        }
        boolean isFavourite = userService.isFavourite(token, postId);
        if(isFavourite){
            log.info("用户已收藏");
        }
        else{
            log.info("用户已收藏");
        }
        return Result.success(isFavourite);
    }

    @GetMapping("/checkPost")
    @Override
    public Result<Boolean> isMyPost(@RequestHeader String token,@RequestParam Integer postId) {
        log.info("正在判断用户是否是该帖子的作者...");
        return Result.success(userService.isMyPost(token, postId));
    }

    @PostMapping("/favourite")
    @Override
    public Result addFavourite(@RequestHeader String token, Integer postId) {
        log.info("正在添加收藏...");
        userService.addFavourite(token, postId);
        //校验postId参数
        if(postId==null||postId<=0){
            throw new IllegalArgumentException("添加的收藏帖子Id无效...");
        }
        log.info("贴子：{}添加收藏成功",postId);
        return Result.success(Result.CREATED);
    }

    @DeleteMapping("/favourite")
    @Override
    public Result removeFavourite(@RequestHeader String token, Integer postId) {
        log.info("正在删除收藏...");
        userService.removeFavourite(token, postId);
        // 校验postId参数
        if (postId == null || postId <= 0) {
            return Result.error(Result.PARAM_ERROR, "删除的帖子ID无效");
        }
        log.info("删除帖子（{}）收藏成功", postId);
        return Result.success(Result.NO_CONTENT);
    }

    @GetMapping("/posts")
    @Override
    public Result<PageResult<Post>> getMyPosts(@RequestHeader String token,QueryParam queryParam) {
        log.info("正在获取用户帖子...");
        PageResult<Post> myPosts = userService.getMyPosts(token, queryParam);
        log.info("获取用户帖子：{}",myPosts);
        return Result.success(myPosts);

    }

    @PostMapping("/updateInfo")
    @Override
    public Result updateUserInfo(@RequestHeader String token,@RequestBody User user) {
        log.info("正在更新用户信息...");
        if(!validationUtil.isValidEmail(user.getEmail())){
            return Result.error(Result.PARAM_ERROR,"用户邮箱格式不符合要求...");
        }
        if (!validationUtil.isValidUsername(user.getUsername())){
            return Result.error(Result.PARAM_ERROR,"用户名格式不符合要求...");
        }
        userService.updateUserInfo(token, user);

        log.info("更新用户信息成功：{}", user);
        return Result.success(Result.NO_CONTENT);
    }

    @PostMapping("/updateAvatar")
    @Override
    public Result<String> updateUserAvatar(@RequestHeader String token, MultipartFile file) throws ClientException {
        log.info("正在更新用户头像...");
        String url = userService.updateUserAvatar(token, file);
        log.info("更新用户头像成功：{}",url);
        return Result.success(url);
    }

    @PostMapping("/updatePassword")
    @Override
    public Result updatePassword(@RequestBody User newUserInfo,@RequestHeader String token) {
        log.info("正在修改密码...");
        if(!validationUtil.isValidEmail(newUserInfo.getEmail())){
            return Result.error(Result.PARAM_ERROR,"用户邮箱格式不符合要求...");
        }
        UserResponse oldUserInfo = userService.getInfo(token);
        String oldUsername=oldUserInfo.getUsername();

        userService.updatePassword(newUserInfo, token);
        log.info("用户({})修改密码({})成功",oldUsername,newUserInfo.getPassword());
        return Result.success(NO_CONTENT);
    }


}
