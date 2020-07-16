package com.dreams.rj.learn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Value("${test.name}")
    private String name;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Test controller /test/hello : " + name;
    }
}
