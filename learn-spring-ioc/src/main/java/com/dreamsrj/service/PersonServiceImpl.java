package com.dreamsrj.service;

import com.dreamsrj.dao.PersonDao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class PersonServiceImpl implements PersonService , InitializingBean, DisposableBean {
    public PersonServiceImpl() {
        System.out.println("创建 PersonServiceImpl()");
    }

    private PersonDao dao;

    public void setDao(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public void getPersonById(String id) {
        dao.getPersonById(id);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Object has been destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("call afterPropertiesSet()");
    }
}
