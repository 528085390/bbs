package com.li.bbs.Service.Imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.li.bbs.Mapper.CommentMapper;
import com.li.bbs.Mapper.UserMapper;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.CommentService;
import com.li.bbs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserMapper userMapper;


    @Override
    public PageResult<CommentResponse> findByPostId(Integer postId, QueryParam queryParam) {
        Page<CommentResponse> p = PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        List<CommentResponse> comments = commentMapper.findByPostId(postId);
        for(CommentResponse c : comments){
            UserResponse author = userMapper.findById(c.getUserId());
            c.setUsername(author.getUsername());
            c.setAvatarUrl(author.getAvatarUrl());
        }
        return new PageResult<>(p.getTotal(), comments);
    }

    @Override
    public void addComment(Integer postId, Comment comment, String token) throws SQLException {
        Integer userId = jwtUtil.extractUserId(token);
        comment.setUserId(userId);
        comment.setPostId(postId);
        comment.setCreatedTime(LocalDateTime.now());
        Integer res = commentMapper.addComment(comment);
        if (res != 1){
            throw new SQLException("添加失败");
        }
    }

    @Override
    public void delete(Integer id) {
        commentMapper.delete(id);
    }
}
