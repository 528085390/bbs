package com.li.bbs.Service;

import com.li.bbs.Pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findByPostId(Integer postId, Integer page, Integer pageSize);

    void addcomment(Comment comment);

    void delete(Integer id);

}
