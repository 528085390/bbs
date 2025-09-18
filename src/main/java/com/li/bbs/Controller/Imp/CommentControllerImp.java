package com.li.bbs.Controller.Imp;
import com.li.bbs.Pojo.Comment;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/comment")
@RestController

public class CommentControllerImp {
    @Autowired
    private CommentService commentService;
    @GetMapping
    public Result getcomment(){
        List<Comment> comments = commentService.findAll();
        // 将数组包装成对象
        Map<String, Object> data = new HashMap<>();
        data.put("comments", comments);
        data.put("count", comments.size());
        return Result.success(data);
    }

    @PostMapping
    public Result addcomment(@RequestBody Comment comment){
        comment.setCreatedTime(LocalDateTime.now());
        commentService.addcomment(comment);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam(value="id",required = false) Integer id){
        commentService.delete(id);
        return Result.success();
    }

}
