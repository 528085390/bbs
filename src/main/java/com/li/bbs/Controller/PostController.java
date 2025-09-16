package com.li.bbs.Controller;

import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;
import com.li.bbs.Pojo.Result;

public interface PostController {

    public Result<Integer> addPost(Post newPost, String token);

    public Result<PageResult<Post>> page(QueryParam queryParam);
}
