package com.li.bbs.Service.Imp;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.li.bbs.Mapper.PostMapper;
import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;
import com.li.bbs.Service.PostService;
import com.li.bbs.util.JwtUtil;
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
    public Post add(Post newPost) {
        newPost.setCreatedTime(LocalDateTime.now());
        newPost.setUpdatedTime(LocalDateTime.now());
        newPost.setViewsCount(0);
        newPost.setCommentsCount(0);
        if(postMapper.addPost(newPost) != 1){
            return null;
        }
        return newPost;
    }

    @Override
    public PageResult<Post> page(QueryParam queryParam) {
        Page<Post> p = PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        List<Post> posts = postMapper.list(queryParam);
        return new PageResult<>(p.getTotal(), posts);

    }

    @Override
    public Post findById(Integer id) {
        postMapper.incrementViewsCount(id);
        return postMapper.findById(id);
    }

    @Override
    public Post update(Post post,Integer id) {
        post.setUpdatedTime(LocalDateTime.now());
        postMapper.update(post);
        return postMapper.findById(id);
    }

    @Override
    public void delete(Integer id) {
        postMapper.delete(id);
    }


}
