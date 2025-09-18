package com.li.bbs.Service;

import com.li.bbs.Pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();

    void addcomment(Comment comment);

    void delete(Integer id);

}
