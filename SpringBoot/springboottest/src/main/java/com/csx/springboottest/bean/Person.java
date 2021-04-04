package com.csx.springboottest.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: User
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/3/1 11:57
 */
@Component
//@ConfigurationProperties(prefix = "person")
@PropertySource(value = {"classpath:person.properties"})
//@ImportResource(locations = {"classpath: spring.xml"})
public class Person {

    @Value("#{10*2}")
    private String name;
    @Value("${person.age}")
    private Integer age;
    @Value("${person.list01}")
    private List<Object> list01;
    private List<Object> list02;
    private Map<Object, Object> map;

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Object> getList01() {
        return list01;
    }

    public void setList01(List<Object> list01) {
        this.list01 = list01;
    }

    public List<Object> getList02() {
        return list02;
    }

    public void setList02(List<Object> list02) {
        this.list02 = list02;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", list01=" + list01 +
                ", list02=" + list02 +
                ", map=" + map +
                '}';
    }
}
