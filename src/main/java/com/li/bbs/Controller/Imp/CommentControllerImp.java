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

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/comment")
@RestController
public class CommentControllerImp implements CommentController {
    @Autowired
    private CommentService commentService;


    @PostMapping("{postId}")
    @Override
    public Result addComment(@PathVariable Integer postId, @Valid @RequestBody Comment comment,@RequestHeader String token) throws SQLException {
        commentService.addComment(postId, comment, token);
        return Result.success(Result.CREATED);
    }

    @GetMapping("{postId}")
    public Result<PageResult<Comment>> getComment(@PathVariable Integer postId,@RequestBody QueryParam queryParam) {
        PageResult<Comment> comments = commentService.findByPostId(postId, queryParam);
        return Result.success(comments);
    }


    @DeleteMapping
    public Result delete(@RequestParam(value = "id", required = false) Integer id) {
        commentService.delete(id);
        return Result.success(Result.NO_CONTENT);
    }
}
