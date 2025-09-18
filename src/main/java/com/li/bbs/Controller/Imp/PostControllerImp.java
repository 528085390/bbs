package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.PostController;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
public class PostControllerImp implements PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    @Override
    public Result<Integer> add(@RequestBody Post newPost, String token) {
        Integer res = postService.add(newPost);
        if (res != 1){
            return Result.error(500,"添加帖子失败");
        }
        return Result.success(res);
    }


    @GetMapping
    @Override
    public Result<PageResult<Post>> page(QueryParam queryParam){
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
    public Result<Post> update(Post post, @PathVariable Integer id) {
        Post update = postService.update(post, id);
        return Result.success(update);
    }

    @DeleteMapping("/{id}")
    @Override
    public Result delete(@PathVariable Integer id) {
        postService.delete(id);
        return Result.success();
    }


}
