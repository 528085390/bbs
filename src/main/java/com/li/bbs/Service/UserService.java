package com.li.bbs.Service;

import com.aliyuncs.exceptions.ClientException;
import com.li.bbs.Pojo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UserService {

    public UserResponse getInfo(String token);

    public PageResult<Post> getFavourites(String token, QueryParam queryParam);

    public void addFavourite(String token, Integer postId);

    public void removeFavourite(String token, Integer postId);

    public PageResult<Post> getMyPosts(String token, QueryParam queryParam);

    void updateUserInfo(String token, User user);

    String updateUserAvatar(String token, MultipartFile file) throws ClientException;



}
