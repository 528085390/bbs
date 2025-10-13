package com.li.bbs.Service;

import com.li.bbs.Pojo.Comment;
import com.li.bbs.Pojo.CommentResponse;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.QueryParam;

import java.sql.SQLException;

public interface CommentService {
    PageResult<CommentResponse> findByPostId(Integer postId, QueryParam queryParam);

    void addComment(Integer postId, Comment comment, String token) throws SQLException;

    void delete(Integer id);

}
