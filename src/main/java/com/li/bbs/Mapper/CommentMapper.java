package com.li.bbs.Mapper;

import com.li.bbs.Pojo.Comment;
import com.li.bbs.Pojo.CommentResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

public interface CommentMapper {
    List<CommentResponse> findByPostId(Integer postId);

    @Insert("insert into comment(post_id,user_id,content,created_time) values(#{postId},#{userId},#{content},#{createdTime})")
    Integer addComment(Comment comment);

    @Delete("delete from comment where id=#{id}")
    void delete(Integer id);

}
