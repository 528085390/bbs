package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.PostController;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.PostService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
public class PostControllerImp implements PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    @Override
    public Result add(@Valid @RequestBody Post newPost, @RequestHeader String token) {
        postService.add(newPost,token);
        return Result.success();
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
    public Result<Post> update(@RequestBody Post post, @PathVariable Integer id) {
        Post update = postService.update(post, id);
        return Result.success(update);
    }

    @DeleteMapping("/{id}")
    @Override
    public Result delete(@PathVariable Integer id,@RequestHeader String token) {
        postService.delete(id, token);
        return Result.success();
    }


}
