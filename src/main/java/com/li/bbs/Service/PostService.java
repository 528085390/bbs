package com.li.bbs.Service;

import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;


public interface PostService {

    public Integer addPost(Post newPost);

     public PageResult<Post> page(QueryParam queryParam);



}
