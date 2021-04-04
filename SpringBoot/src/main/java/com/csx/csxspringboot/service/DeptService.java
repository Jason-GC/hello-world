package com.csx.csxspringboot.service;

import com.csx.csxspringboot.bean.Dept;
import com.csx.csxspringboot.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DeptService
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/3/29 23:42
 */
@Service
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 获取所有dept信息
     * @Author Cuisx
     * @Date 17:53 2021/4/4
     * @param
     * @return java.util.List<com.csx.csxspringboot.bean.Dept>
     */
    public List<Dept> getAllDeptInfo(){

        List<Dept> allInfo = deptMapper.getAllInfo();
        return allInfo;
    }

}
