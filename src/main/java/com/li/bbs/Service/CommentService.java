package com.li.bbs.Service;

import com.li.bbs.Pojo.Comment;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.QueryParam;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface CommentService {
    PageResult<Comment> findByPostId(Integer postId, QueryParam queryParam);

    void addComment(Integer postId,Comment comment, String token);

    void delete(Integer id);

}
