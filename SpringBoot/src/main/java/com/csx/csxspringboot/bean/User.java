package com.csx.csxspringboot.bean;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: User
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/3/27 20:32
 */
@Data
@Component
@Log4j2
public class User {

    public User(){
        log.info("User构造函数");
    }

    @ExcelProperty
    private String userName;
    @ExcelProperty
    private Integer userAge;
    @ExcelProperty
    private String userAddress;
    @ExcelProperty
    private Date createDate;
    @ExcelIgnore
    private String ignore;

}
