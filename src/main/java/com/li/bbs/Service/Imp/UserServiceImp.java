package com.li.bbs.Service.Imp;


import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;
import com.li.bbs.Pojo.User;
import com.li.bbs.Service.UserService;

public class UserServiceImp implements UserService {
    @Override
    public User getInfo(Integer userId) {
        return null;
    }

    @Override
    public PageResult<Post> getFavourites(Integer userId) {
        return null;
    }

    @Override
    public Post addFavourite(Integer userId, Integer postId) {
        return null;
    }

    @Override
    public Post removeFavourite(Integer userId, Integer postId) {
        return null;
    }
}
