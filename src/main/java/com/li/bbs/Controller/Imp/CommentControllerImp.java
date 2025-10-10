package com.li.bbs.Controller.Imp;

import com.github.pagehelper.PageInfo;
import com.li.bbs.Controller.CommentController;
import com.li.bbs.Pojo.Comment;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Service.CommentService;
import com.li.bbs.util.ValidationUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RequestMapping("/comment")
@RestController
public class CommentControllerImp implements CommentController {
    @Autowired
    private CommentService commentService;


    @PostMapping("{postId}")
    @Override
    public Result addComment(@PathVariable Integer postId, @Valid @RequestBody Comment comment,@RequestHeader String token) throws SQLException {
        ValidationUtil validationUtil = new ValidationUtil();

        //参数校验
        if(postId==null||postId<=0){
            throw new IllegalArgumentException("帖子Id无效...");
        }
        if(comment==null){
            throw new IllegalArgumentException("评论内容不能为空...");
        }
        if(!validationUtil.isValidComment(comment.getContent())){
            throw new IllegalArgumentException("评论内容不符合要求：包含敏感字符...");
        }
       if(!validationUtil.isValidComment(comment.getContent())){
           return Result.error(Result.PARAM_ERROR,"评论内容不符合要求：不能为空、长度不超过200字、不能包含敏感字符...");
       }

        commentService.addComment(postId, comment, token);
        log.info("添加评论：{}",comment);
        return Result.success(Result.CREATED);
    }

    @Override
    @GetMapping("{postId}")
    public Result<PageResult<Comment>> getComment(@PathVariable Integer postId,@RequestBody QueryParam queryParam) {
        //参数校验
        if(postId==null||postId<=0){
            throw new IllegalArgumentException("帖子Id无效...");
        }
        PageResult<Comment> comments = commentService.findByPostId(postId, queryParam);
        log.info("查询评论：{}",comments);
        return Result.success(comments);
    }


    @DeleteMapping
    public Result delete(@RequestParam(value = "id", required = false) Integer id) {
        //参数校验
        if(id==null||id<=0){
            throw new IllegalArgumentException("评论Id无效...");
        }
        commentService.delete(id);
        log.info("删除评论：{}",id);
        return Result.success(Result.NO_CONTENT);
    }
}
