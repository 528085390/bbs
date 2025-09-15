package com.li.bbs.Service;


import com.li.bbs.Pojo.Emp;
import com.li.bbs.Pojo.EmpQueryParam;
import com.li.bbs.Pojo.PageResult;

public interface EmpService {
    //分页查询
/*    page ：页码
    pageSize: 每页记录数*/
    //PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);


    PageResult<Emp> page(EmpQueryParam empQueryParam);

}
