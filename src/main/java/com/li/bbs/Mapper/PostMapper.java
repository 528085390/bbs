package com.li.bbs.Mapper;

import com.li.bbs.Pojo.PostResponse;
import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PostMapper {

    @Insert("insert into post(title,subtitle,content,user_id,board_id,views_count,comments_count,created_time,updated_time)" +
            "values(#{title},#{subtitle},#{content},#{userId},#{boardId},0,0,#{createdTime},#{updatedTime})")
    public Integer addPost(Post newPost);

    public List<PostResponse> list(QueryParam queryParam);

    @Select("select * from post where id=#{id}")
    public PostResponse findById(Integer id);

    @Update("UPDATE post SET views_count = views_count + 1 WHERE id = #{id}")
    public void incrementViewsCount(Integer id);

    @Update("UPDATE post SET title = #{title}, subtitle = #{subtitle}, content  = #{content}, updated_time = #{updatedTime} WHERE id = #{id}")
    public Integer update(Post post);

    @Delete("delete from post where id=#{id}")
    public Integer delete(Integer id);

    @Select("select *from post order by views_count desc")
    List<PostResponse> hotpageViews(QueryParam queryParam);
}
