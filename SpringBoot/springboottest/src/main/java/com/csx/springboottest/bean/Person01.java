package com.csx.springboottest.bean;

/**
 * @ClassName: Person01
 * @Description: TODO
 * @author: Cuisx
 * @date: 2021/3/1 13:14
 */
public class Person01 {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person01{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private Integer age;

}
