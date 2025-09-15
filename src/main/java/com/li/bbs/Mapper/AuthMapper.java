package com.li.bbs.Mapper;


import com.li.bbs.Pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {

    @Insert("insert into user(username,password,created_time,updated_time) values(#{username},#{password},#{createdTime},#{updatedTime})")
    public Integer register(User newUserInfo);
}
