package com.li.bbs.Service.Imp;


import com.li.bbs.Mapper.PostMapper;
import com.li.bbs.Pojo.Post;
import com.li.bbs.Service.PostService;
import com.li.bbs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PostMapper postMapper;

    @Override
    public Integer addPost(Post newPost) {
        Integer res = postMapper.addPost(newPost);
        return res;
    }





}
