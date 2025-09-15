package com.li.bbs.Mapper;

import com.li.bbs.Pojo.Comment;
import com.li.bbs.Pojo.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PostMapper {

    @Insert("insert into post(title,subtitle,content,user_id,board_id,views_count,comments_count,created_time,updated_time)" +
            "values(#{title},#{subtitle},#{content},#{userId},#{boardId},0,0,#{createdTime},#{updatedTime})")
    public Integer addPost(Post newPost);
}
