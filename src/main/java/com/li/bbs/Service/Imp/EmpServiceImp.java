package com.li.bbs.Service.Imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.li.bbs.Mapper.EmpMapper;
import com.li.bbs.Pojo.Emp;
import com.li.bbs.Pojo.EmpQueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmpServiceImp implements EmpService {
    @Autowired
    private EmpMapper empMapper ;

/*    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page,pageSize);
        List<Emp> empList= empMapper.list(name,gender,begin,end);
        Page<Emp> p=(Page<Emp>) empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }*/

    //条件分页查询（封装函数）
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> empList= empMapper.list(empQueryParam);
        Page<Emp> p=(Page<Emp>) empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }
}
