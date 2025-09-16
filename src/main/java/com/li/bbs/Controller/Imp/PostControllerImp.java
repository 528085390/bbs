package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.PostController;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostControllerImp implements PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    @Override
    public Result<Integer> addPost(@RequestBody Post newPost, String token) {
        Integer res = postService.addPost(newPost);
        if (res != 1){
            return Result.error(500,"添加帖子失败");
        }
        return Result.success(res);
    }

    @GetMapping
    public Result<PageResult<Post>> page(QueryParam queryParam){
        PageResult<Post> pageResult= postService.page(queryParam);
        return Result.success(pageResult);
    }
}
