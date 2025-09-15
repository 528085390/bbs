package com.li.bbs.Service;

import com.li.bbs.Pojo.Post;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface PostService {

    public Integer addPost(Post newPost);



}
