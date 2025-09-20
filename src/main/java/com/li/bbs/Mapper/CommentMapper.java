package com.li.bbs.Mapper;

import com.li.bbs.Pojo.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface CommentMapper {
    List<Comment> findByPostId(Integer postId);


    @Insert("insert into comment(post_id,user_id,content,created_time) values(#{postId},#{userId},#{content},#{createdTime})")
    void addcomment(Comment comment);
    @Delete("delete from comment where id=#{id}")
    void delete(Integer id);
}
