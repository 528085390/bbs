package com.li.bbs.Mapper;

import com.li.bbs.Pojo.Emp;
import com.li.bbs.Pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {
/*    //查询总记录数
    @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
    public Long count();*/
    //分页查询
/*
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by " +
            "e.update_time desc")
    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
*/
     public List<Emp> list(EmpQueryParam empQueryParam);
}