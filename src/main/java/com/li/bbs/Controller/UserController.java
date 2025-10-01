package com.li.bbs.Controller;

import com.li.bbs.Pojo.*;
import org.springframework.security.core.parameters.P;


public interface UserController {

    Result<UserResponse> getInfo(String token);

    Result<PageResult<Post>> getFavourites(String token, QueryParam queryParam);

    Result addFavourite(String token, Integer postId);

    Result removeFavourite(String token, Integer postId);

    Result<PageResult<Post>> getMyPosts(String token, QueryParam queryParam);


}
