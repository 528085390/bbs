package com.li.bbs.Mapper;


import com.li.bbs.Pojo.Post;
import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.User;
import com.li.bbs.Pojo.UserResponse;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    UserResponse findById(Integer id);


    @Insert("insert into favourite(user_id,post_id,created_time) values(#{userId},#{postId},#{createdTime})")
    Integer addFavourite(Integer userId, Integer postId, LocalDateTime createdTime);

    @Delete("delete from favourite where user_id = #{userId} and post_id = #{postId}")
    Integer removeFavourite(Integer userId, Integer postId);

    @Select("select p.* " +
            "FROM post p " +
            "INNER JOIN favourite f " +
            "ON p.id = f.post_id " +
            "WHERE  f.user_id = #{userId} " +
            "ORDER BY p.created_time DESC")
    List<Post> findAllFavourites(Integer userId);


    @Select("select * from post where user_id = #{userId}")
    List<Post> findPostsByUserId(Integer userId);

    Integer updateUserInfo(User user);

    @Update("update user set avatar_url = #{avatarUrl} where id = #{userId}")
    Integer updateUserAvatar(Integer userId, String avatarUrl);
}
