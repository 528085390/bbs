package com.li.bbs.Controller;

import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;
import com.li.bbs.Pojo.Result;

public interface PostController {

    public Result<Integer> add(Post newPost, String token);

    public Result<PageResult<Post>> page(QueryParam queryParam);

    public Result<Post> findById(Integer id);

    public Result<Post> update(Post post, Integer id);

    public Result delete(Integer id);
}
