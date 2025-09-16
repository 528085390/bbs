package com.li.bbs.Mapper;

import com.li.bbs.Pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

public interface CommentMapper {
    @Select("select * from comment order by created_time desc")
    List<Comment> findAll();
}
