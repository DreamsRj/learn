package com.dreams.rj.learn.dao;

import com.dreams.rj.learn.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public User getUserById(String id) {
        User user = new User();
        user.setId(id);
        user.setAge(23);
        user.setName("dreamsrj");
        user.setAddr("chengdu");
        return user;
    }



}
