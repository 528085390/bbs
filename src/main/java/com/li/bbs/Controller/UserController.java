package com.li.bbs.Controller;

import com.aliyuncs.exceptions.ClientException;
import com.li.bbs.Pojo.*;
import org.springframework.security.core.parameters.P;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


public interface UserController {

    Result<UserResponse> getInfo(String token);

    Result<PageResult<Post>> getFavourites(String token, QueryParam queryParam);

    Result addFavourite(String token, Integer postId);

    Result removeFavourite(String token, Integer postId);

    Result<PageResult<Post>> getMyPosts(String token, QueryParam queryParam);

    Result updateUserInfo(String token, User user);

    Result<String> updateUserAvatar(String token, MultipartFile file) throws ClientException;

    public Result updatePassword(User newUserInfo,String  token);

    Result<Boolean> isFavourite(String token,Integer postId);

    Result<Boolean> isMyPost(String token,Integer postId);
}
