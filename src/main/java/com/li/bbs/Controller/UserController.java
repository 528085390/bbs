package com.li.bbs.Controller;

import com.li.bbs.Pojo.*;


public interface UserController {

    Result<UserResponse> getInfo(String token);

    Result<PageResult<Post>> getFavourites(String token, QueryParam queryParam);

    Result addFavourite(String token, Integer postId);

    Result removeFavourite(String token, Integer postId);


}
