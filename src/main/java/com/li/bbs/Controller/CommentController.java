package com.li.bbs.Controller;


import com.li.bbs.Pojo.Comment;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.Result;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommentController {
    Result addComment(Integer postId,Comment comment, String token);
    Result<PageResult<Comment>> getComment(Integer postId, QueryParam queryParam);

}
