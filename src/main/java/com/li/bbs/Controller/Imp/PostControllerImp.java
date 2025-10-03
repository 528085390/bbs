package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.PostController;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.PostService;
import com.li.bbs.util.ValidationUtil;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
public class PostControllerImp implements PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ValidationUtil validationUtil;

    @PostMapping
    @Override
    public Result add(@Valid @RequestBody Post newPost, @RequestHeader String token) {
        postService.add(newPost,token);
        return Result.success(Result.CREATED);
    }


    @GetMapping
    @Override
    public Result<PageResult<Post>> page(@RequestBody QueryParam queryParam){
        PageResult<Post> pageResult= postService.page(queryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    @Override
    public Result<Post> findById(@PathVariable Integer id) {
        Post postById = postService.findById(id);
        return Result.success(postById);
    }

    @PutMapping("/{id}")
    @Override
    public Result update(@RequestBody Post post, @PathVariable Integer id,@RequestHeader String token) {
        postService.update(post, id, token);
        return Result.success(Result.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Override
    public Result delete(@PathVariable Integer id,@RequestHeader String token) {
        postService.delete(id, token);
        return Result.success(Result.NO_CONTENT);
    }


}
