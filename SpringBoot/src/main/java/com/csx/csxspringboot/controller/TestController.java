package com.csx.csxspringboot.controller;

import com.alibaba.fastjson.JSON;
import com.csx.csxspringboot.bean.Dept;
import com.csx.csxspringboot.bean.User;
import com.csx.csxspringboot.mapper.DeptMapper;
import com.csx.csxspringboot.service.DeptService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HelloWorldController
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/3/27 19:51
 */
@RestController
@Log4j2
public class TestController {

    @Autowired
    private User user;
    @Autowired
    private DeptService deptService;

    @GetMapping(value = "/getAllDeptInfo")
    public List<Dept> getDeptAllInfo(){
        List<Dept> allDeptInfo = deptService.getAllDeptInfo();
        log.info("allDeptInfo>>>>>>>>>> "+allDeptInfo);

        //分页
        PageHelper.startPage(1,10);
        return allDeptInfo;
    }

    @ResponseBody
    @GetMapping("/getAllDept")
    public PageInfo getDepts(){

        Page page = PageHelper.startPage(1,2);
        List<Dept> list = new ArrayList<>();
        list.add(new Dept(1,"csx"));
        list.add(new Dept(2,"Cuisx"));
        list.add(new Dept(3,"崔大祥"));

        log.info(page.getResult());

        PageInfo<Dept> pageInfo = new PageInfo<>(list);
        List<Dept> list1 = pageInfo.getList();
        System.out.println(list1.get(2));
        log.info(page);
        log.info(JSON.toJSON(pageInfo));
        return pageInfo;
    }

    @GetMapping("/getUser")
    public User getUser(){
        user.setUserName("csx");
        user.setUserAge(24);
        return user;
    }

    @PostMapping("/setUser")
    public User setUser(@Validated @RequestBody User user){
        user.setUserName(user.getUserName());
        user.setUserAge(user.getUserAge());
        return user;
    }

}
