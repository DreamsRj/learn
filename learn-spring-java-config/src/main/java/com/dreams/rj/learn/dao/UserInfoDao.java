package com.dreams.rj.learn.dao;

import com.dreams.rj.learn.User;

public class UserInfoDao {

    public User findUser(String id){
        User user = new User();
        user.setId(id);
        user.setAge(23);
        user.setName("dreams.rj");
        user.setAddr("chengdu");
        return user;
    }
}
