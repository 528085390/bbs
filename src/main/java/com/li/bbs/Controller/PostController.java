package com.li.bbs.Controller;

import com.li.bbs.Pojo.*;

public interface PostController {

    public Result<Post> add(Post newPost, String token);

    public Result<PageResult<PostResponse>> page(QueryParam queryParam);

    public Result<PostResponse> findById(Integer id);

    public Result<Post> update(Post post, Integer id, String token);

    public Result delete(Integer id, String token);
}
