package com.li.bbs.Controller.Imp;

import com.li.bbs.Controller.CommentController;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.CommentService;
import com.li.bbs.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@RequestMapping("/comment")
@RestController
public class CommentControllerImp implements CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    ValidationUtil validationUtil;

    @PostMapping("{postId}")
    @Override
    public Result addComment(@PathVariable Integer postId, @RequestBody Comment comment, @RequestHeader String token) throws SQLException {


        //参数校验
        if (postId == null || postId <= 0) {
            throw new IllegalArgumentException("帖子Id无效...");
        }
        if (comment == null) {
            throw new IllegalArgumentException("评论内容不能为空...");
        }
        if (!validationUtil.isValidComment(comment.getContent())) {
            throw new IllegalArgumentException("评论内容不符合要求：包含敏感字符...");
        }
        if (!validationUtil.isValidComment(comment.getContent())) {
            return Result.error(Result.PARAM_ERROR, "评论内容不符合要求：不能为空、长度不超过200字、不能包含敏感字符...");
        }

        commentService.addComment(postId, comment, token);
        log.info("添加评论：{}", comment);
        return Result.success(Result.CREATED);
    }

    @Override
    @GetMapping("{postId}")
    public Result<PageResult<CommentResponse>> getComment(@PathVariable Integer postId, QueryParam queryParam) {
        //参数校验
        if (postId == null || postId <= 0) {
            throw new IllegalArgumentException("    帖子Id无效...");
        }
        PageResult<CommentResponse> comments = commentService.findByPostId(postId, queryParam);
        log.info("查询评论：{}", comments);
        return Result.success(comments);
    }


    @Override
    @DeleteMapping({"{id}"})
    public Result delete(@PathVariable Integer id) {
        //参数校验
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("评论Id无效...");
        }
        commentService.delete(id);
        log.info("删除评论：{}", id);
        return Result.success(Result.NO_CONTENT);
    }
}
