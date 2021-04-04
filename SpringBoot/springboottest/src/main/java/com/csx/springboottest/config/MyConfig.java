package com.csx.springboottest.config;

import com.csx.springboottest.bean.Person01;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MyConfig
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/3/1 13:10
 */
@Configuration
public class MyConfig {

    @Bean //将方法的返回值添加到IOC容器中，组件名称默认为方法名
    public Person01 ppppp() {
        System.out.println("@Bean注解给ioc中添加组件");
        return new Person01();
    }
}
