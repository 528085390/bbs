package com.li.bbs.Service.Imp;

import com.li.bbs.Mapper.CommentMapper;
import com.li.bbs.Pojo.Comment;
import com.li.bbs.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class CommentServiceImp implements CommentService {

    @Autowired
    public CommentMapper commentMapper;
    @Override
    public List<Comment> findAll() {
        return commentMapper.findAll();
    }

    @Override
    public void addcomment(Comment comment) {
        comment.setCreatedTime(LocalDateTime.now());
        commentMapper.addcomment(comment);
    }

    @Override
    public void delete(Integer id) {
        commentMapper.delete(id);
    }
}
