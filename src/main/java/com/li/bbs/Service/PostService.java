package com.li.bbs.Service;

import com.li.bbs.Pojo.EmpQueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;


public interface PostService {

    public Integer addPost(Post newPost);

     public PageResult<Post> page(EmpQueryParam empQueryParam);



}
