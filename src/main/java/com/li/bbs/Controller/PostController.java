package com.li.bbs.Controller;

import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;
import com.li.bbs.Pojo.Result;

public interface PostController {

    public Result<Post> add(Post newPost, String token);

    public Result<PageResult<Post>> page(QueryParam queryParam);

    public Result<Post> findById(Integer id);

    public Result<Post> update(Post post, Integer id, String token);

    public Result delete(Integer id, String token);
}
