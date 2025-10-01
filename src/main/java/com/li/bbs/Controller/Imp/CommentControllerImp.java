package com.li.bbs.Controller.Imp;

import com.github.pagehelper.PageInfo;
import com.li.bbs.Controller.CommentController;
import com.li.bbs.Pojo.Comment;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/comment")
@RestController
public class CommentControllerImp implements CommentController {
    @Autowired
    private CommentService commentService;
    /*@GetMapping()
    public Result getcomment(@RequestParam(value="post_id",required = false)Integer post_id){

        List<Comment> comments = commentService.findByPostId(post_id);
        // 将数组包装成对象
        Map<String, Object> data = new HashMap<>();
        data.put("comments", comments);
        data.put("count", comments.size());
        return Result.success(data);
    }*/

    @PostMapping("{postId}")
    @Override
    public Result addComment(@PathVariable Integer postId, @Valid @RequestBody Comment comment,@RequestHeader String token) {
        commentService.addComment(postId, comment, token);
        return Result.success();
    }

    @GetMapping("{postId}")
    public Result<PageResult<Comment>> getComment(@PathVariable Integer postId,@RequestBody QueryParam queryParam) {
        PageResult<Comment> comments = commentService.findByPostId(postId, queryParam);
        return Result.success(comments);
    }


    @DeleteMapping
    public Result delete(@RequestParam(value = "id", required = false) Integer id) {
        commentService.delete(id);
        return Result.success();
    }
}
