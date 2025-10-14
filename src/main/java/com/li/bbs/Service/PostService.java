package com.li.bbs.Service;

import com.li.bbs.Pojo.PostResponse;
import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;


public interface PostService {

    void add(Post newPost, String token);

    public PageResult<PostResponse> page(QueryParam queryParam);

     public PostResponse findById(Integer id);

     public void update(Post post, Integer Id, String token) ;

     public void delete(Integer id, String token);


    PageResult<PostResponse> hotpageViews(QueryParam queryParam);
}
