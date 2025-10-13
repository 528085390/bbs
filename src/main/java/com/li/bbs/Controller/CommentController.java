package com.li.bbs.Controller;


import com.li.bbs.Pojo.*;

import java.sql.SQLException;

public interface CommentController {
    Result addComment(Integer postId,Comment comment, String token) throws SQLException;
    Result<PageResult<CommentResponse>> getComment(Integer postId, QueryParam queryParam);
    Result delete(Integer id);
}
