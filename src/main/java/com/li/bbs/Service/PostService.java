package com.li.bbs.Service;

import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;


public interface PostService {

    public Integer add(Post newPost);

     public PageResult<Post> page(QueryParam queryParam);

     public Post findById(Integer id);

     public Post update(Post post, Integer Id) ;

     public void delete(Integer id);



}
