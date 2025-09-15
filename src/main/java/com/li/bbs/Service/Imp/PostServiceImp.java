package com.li.bbs.Service.Imp;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.li.bbs.Mapper.PostMapper;
import com.li.bbs.Pojo.EmpQueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Post;
import com.li.bbs.Service.PostService;
import com.li.bbs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PageResult<Post> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Post> postList= postMapper.list(empQueryParam);
        Page<Post> p=(Page<Post>) postList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }





}
