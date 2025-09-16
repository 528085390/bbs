package com.li.bbs.Service.Imp;

import com.li.bbs.Mapper.CommentMapper;
import com.li.bbs.Pojo.Comment;
import com.li.bbs.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CommentServiceImp implements CommentService {
    @Autowired
    public CommentMapper commentMapper;
    @Override
    public List<Comment> findAll() {
        return commentMapper.findAll();
    }
}
