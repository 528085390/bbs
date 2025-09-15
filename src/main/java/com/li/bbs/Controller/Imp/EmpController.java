package com.li.bbs.Controller.Imp;


import com.li.bbs.Pojo.Emp;
import com.li.bbs.Pojo.EmpQueryParam;
import com.li.bbs.Pojo.PageResult;
import com.li.bbs.Pojo.Result;
import com.li.bbs.Service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
/*    @GetMapping
    public Result page(Integer page, Integer pageSize,
                       String name, Integer gender,
                       @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate begin,
                       @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate end){
        System.out.println("分页查询:"+page+"  " +pageSize+"  " +name+"  " +gender+"  " +begin+"  " +end);
        PageResult<Emp> pageResult= empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageResult);
    }*/

    @GetMapping
    public Result<PageResult> page(EmpQueryParam empQueryParam){
        System.out.println("分页查询:"+empQueryParam);
        PageResult<Emp> pageResult= empService.page(empQueryParam);
        return Result.success(pageResult);
    }


}
