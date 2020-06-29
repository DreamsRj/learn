package com.dreams.rj.learn.service;

import com.dreams.rj.learn.User;
import com.dreams.rj.learn.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUserById(String id) {
        return userDao.getUserById(id);
    }

}
