package com.li.bbs.Service;

import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;
import com.li.bbs.Pojo.User;

import javax.swing.plaf.PanelUI;
import java.security.PublicKey;

public interface UserService {

    public User getInfo(Integer userId);

    public PageResult<Post> getFavourites(Integer userId);

    public Post addFavourite(Integer userId,Integer postId);

    public Post removeFavourite(Integer userId,Integer postId);


}
