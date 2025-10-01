package com.li.bbs.Service;

import com.li.bbs.Pojo.*;

public interface UserService {

    public UserResponse getInfo(String token);

    public PageResult<Post> getFavourites(String token, QueryParam queryParam);

    public void addFavourite(String token, Integer postId);

    public void removeFavourite(String token, Integer postId);

    public PageResult<Post> getMyPosts(String token, QueryParam queryParam);



}
