package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.PostController;
import com.li.bbs.Pojo.Post;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostControllerImp implements PostController {

    @Autowired
    private PostService postService;

    @Override
    public Result<Integer> addPost(Post newPost, String token) {
        return null;
    }
}
