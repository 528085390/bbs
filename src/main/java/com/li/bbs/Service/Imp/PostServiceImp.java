package com.li.bbs.Service.Imp;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.li.bbs.Exception.NoResourceFoundException;
import com.li.bbs.Mapper.PostMapper;
import com.li.bbs.Mapper.UserMapper;
import com.li.bbs.Pojo.*;
import com.li.bbs.Service.PostService;
import com.li.bbs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void add(Post newPost, String token) {
        Integer userId = jwtUtil.extractUserId(token);
        newPost.setUserId(userId);
        newPost.setCreatedTime(LocalDateTime.now());
        newPost.setUpdatedTime(LocalDateTime.now());
        Integer res = postMapper.addPost(newPost);
        if (res != 1) {
            throw new RuntimeException("添加失败");
        }
    }

    @Override
    public PageResult<PostResponse> page(QueryParam queryParam) {
        Page<Post> page = PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        List<PostResponse> posts = postMapper.list(queryParam);
        for (PostResponse p : posts){
            Integer userId = p.getUserId();
            UserResponse user = userMapper.findById(userId);
            p.setUsername(user.getUsername());
            p.setAvatarUrl(user.getAvatarUrl());
            p.setUserId(user.getId());
        }

        return new PageResult<>(page.getTotal(), posts);

    }

    @Transactional
    @Override
    public PostResponse findById(Integer id) {
        PostResponse post = postMapper.findById(id);
        if (post == null){
            throw new NoResourceFoundException("没有此帖子");
        }
        UserResponse author = userMapper.findById(post.getUserId());
        post.setUsername(author.getUsername());
        post.setAvatarUrl(author.getAvatarUrl());
        post.setUserId(author.getId());
        postMapper.incrementViewsCount(id);
        return post;
    }

    @Override
    public void update(Post post, Integer id, String token) {
        Integer userId = jwtUtil.extractUserId(token);
        if (!postMapper.findById(id).getUserId().equals(userId)) {
            throw new BadCredentialsException("没有权限");
        }
        post.setUpdatedTime(LocalDateTime.now());
        postMapper.update(post);
    }

    @Transactional
    @Override
    public void delete(Integer id,String token) {
        Integer userId = jwtUtil.extractUserId(token);
        if (!postMapper.findById(id).getUserId().equals(userId)) {
            throw new RuntimeException("没有权限");
        }
        Integer res = postMapper.delete(id);
        if (res != 1) {
            throw new RuntimeException("删除失败");
        }
    }


}
