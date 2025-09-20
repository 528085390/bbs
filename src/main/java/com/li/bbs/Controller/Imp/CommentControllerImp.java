package com.li.bbs.Controller.Imp;

import com.github.pagehelper.PageInfo;
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
    /*@GetMapping()
    public Result getcomment(@RequestParam(value="post_id",required = false)Integer post_id){

        List<Comment> comments = commentService.findByPostId(post_id);
        // 将数组包装成对象
        Map<String, Object> data = new HashMap<>();
        data.put("comments", comments);
        data.put("count", comments.size());
        return Result.success(data);
    }*/

    @GetMapping()
    public Result getcomment(Comment comment) {
        // 设置默认分页参数
        if (comment.getPage() == null || comment.getPage() < 1) {
            comment.setPage(1);
        }
        if (comment.getPageSize() == null || comment.getPageSize() < 1) {
            comment.setPageSize(10);
        }

        // 使用PageHelper进行分页查询
        List<Comment> comments = commentService.findByPostId(
                comment.getPostId(),
                comment.getPage(),
                comment.getPageSize()
        );

        // 使用PageInfo获取分页信息
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);

        // 将分页数据包装成对象
        Map<String, Object> data = new HashMap<>();
        data.put("comments", comments);
        data.put("total", pageInfo.getTotal());
        data.put("page", pageInfo.getPageNum());
        data.put("pageSize", pageInfo.getPageSize());
        data.put("pages", pageInfo.getPages());
        data.put("hasNext", pageInfo.isHasNextPage());
        data.put("hasPrevious", pageInfo.isHasPreviousPage());
        return Result.success(data);
    }


    @PostMapping
    public Result addcomment( @RequestBody Comment comment){
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
