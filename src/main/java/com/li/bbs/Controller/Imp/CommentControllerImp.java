package com.li.bbs.Controller.Imp;
import com.li.bbs.Pojo.Comment;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
