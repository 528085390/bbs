package com.li.bbs.Service.Imp;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.li.bbs.Mapper.PostMapper;
import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;
import com.li.bbs.Service.PostService;
import com.li.bbs.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PostMapper postMapper;

    @Override
    public Integer addPost(Post newPost) {
        newPost.setCreatedTime(LocalDateTime.now());
        newPost.setUpdatedTime(LocalDateTime.now());
        newPost.setViewsCount(0);
        newPost.setCommentsCount(0);
        Integer res = postMapper.addPost(newPost);
        return res;
    }

    @Override
    public PageResult<Post> page(QueryParam queryParam) {
        Page<Post> p = PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        List<Post> posts = postMapper.list(queryParam);
        return new PageResult<>(p.getTotal(), posts);

    }


}
