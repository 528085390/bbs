package com.li.bbs.Mapper;


import com.li.bbs.Pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findById(Integer id);





}
