package com.dreamsrj.service;

import com.dreamsrj.dao.PersonDao;

public class UserServiceImpl implements UserService{
    private UserServiceImpl(){
        System.out.println("创建 UserServiceImpl()");
    }

    private PersonDao personDao;

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void getUser(String id) {
        System.out.println("User service return user");
    }
}
