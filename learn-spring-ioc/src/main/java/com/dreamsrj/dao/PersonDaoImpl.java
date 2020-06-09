package com.dreamsrj.dao;

public class PersonDaoImpl implements PersonDao {
    public PersonDaoImpl() {
        System.out.println("创建 PersonDaoImpl()");
    }

    @Override
    public void getPersonById(String id) {
        System.out.println("person dao impl get person by id" + id);
    }
}
