package com.dreamsrj.autowired;

import com.dreamsrj.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class TestAutowired {
    @Autowired
    private PersonService personService;

    @Resource
    public void test(PersonService personService){

    }
}
