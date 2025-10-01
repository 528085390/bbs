package com.li.bbs.Mapper;


import com.li.bbs.Pojo.Post;
import com.li.bbs.Pojo.QueryParam;
import com.li.bbs.Pojo.User;
import com.li.bbs.Pojo.UserResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    UserResponse findById(Integer id);


    @Insert("insert into favourite(user_id,post_id,created_time) values(#{userId},#{postId},#{createdTime})")
    void addFavourite(Integer userId, Integer postId, LocalDateTime createdTime);

    @Delete("delete from favourite where user_id = #{userId} and post_id = #{postId}")
    void removeFavourite(Integer userId, Integer postId);

    List<Integer> findAllFavouritePostId(Integer userId);

    @Select("select p.* " +
            "FROM post p " +
            "INNER JOIN favourite f " +
            "ON p.id = f.post_id " +
            "WHERE  f.user_id = #{userId} " +
            "ORDER BY p.created_time DESC")
    List<Post> findAllFavourites(Integer userId);


    void updateUser(User user);
}
