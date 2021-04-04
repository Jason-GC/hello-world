package com.csx.springboottest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloWorld
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/3/1 11:29
 */
//@ResponseBody
//@Controller
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello Spring Boot!!!");

        return "Hello Spring Boot!!!";
    }

}
