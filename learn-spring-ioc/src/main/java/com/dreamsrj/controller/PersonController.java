package com.dreamsrj.controller;

import com.dreamsrj.service.PersonService;

public class PersonController {

    public PersonController(){
        System.out.println("创建 PersonController");
    }

    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void getPersion() {
        personService.getPersonById("123123");
    }

}
