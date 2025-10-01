package com.li.bbs.Service.Imp;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.li.bbs.Exception.NoResourceFoundException;
import com.li.bbs.Mapper.PostMapper;
import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;
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
    public PageResult<Post> page(QueryParam queryParam) {
        Page<Post> p = PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        List<Post> posts = postMapper.list(queryParam);
        return new PageResult<>(p.getTotal(), posts);

    }

    @Transactional
    @Override
    public Post findById(Integer id) {
        Post post = postMapper.findById(id);
        if (post == null){
            throw new NoResourceFoundException("没有此帖子");
        }
        postMapper.incrementViewsCount(id);
        return post;
    }

    @Override
    public Post update(Post post,Integer id,String token) {
        Integer userId = jwtUtil.extractUserId(token);
        if (!postMapper.findById(id).getUserId().equals(userId)) {
            throw new BadCredentialsException("没有权限");
        }
        post.setUpdatedTime(LocalDateTime.now());
        postMapper.update(post);
        return postMapper.findById(id);
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
