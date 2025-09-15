package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.PostController;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostControllerImp implements PostController {

    @Autowired
    private PostService postService;

    @Override
    public Result<Integer> addPost(Post newPost, String token) {
        return null;
    }

    @GetMapping
    public Result<PageResult> page(EmpQueryParam empQueryParam){
        System.out.println("分页查询:"+empQueryParam);
        PageResult<Post> pageResult= postService.page(empQueryParam);
        return Result.success(pageResult);
    }
}
