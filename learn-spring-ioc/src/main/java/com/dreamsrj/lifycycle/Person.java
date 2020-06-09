package com.dreamsrj.lifycycle;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * 演示初始化相关配置
 */
public class Person {
    private String name;
    private int age;
    private int height;

    public Person() {
        System.out.println("创建Person实例...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("set person name " + name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        System.out.println("set person age " + age);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        System.out.println("set person height " + height);
    }

    /**
     * 使用注解，设置初始化方法（在构造完成后调用）
     */
    @PostConstruct
    public void init() {
        System.out.println("call init()");
    }
    /**
     * 使用注解方式配置Bean及初始化方法
     */
//    @Bean(initMethod = "init()")
//    public void init(){
//        System.out.println("call init() by init-method");
//    }
}
