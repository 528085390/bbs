package com.li.bbs.Mapper;


import com.li.bbs.Pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface AuthMapper {

    @Insert("insert into user(username,password,created_time,updated_time) values(#{username},#{password},#{createdTime},#{updatedTime})")
    public void addUser(User newUserInfo);

    @Select("select * from user where username=#{username}")
    public User findByUsername(String username);


    @Update("update user set updated_time=#{now} where id=#{userId}")
    void updateTime(LocalDateTime now, Integer userId);
}
