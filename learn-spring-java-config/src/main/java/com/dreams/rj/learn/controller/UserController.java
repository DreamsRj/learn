package com.dreams.rj.learn.controller;


import com.dreams.rj.learn.User;
import com.dreams.rj.learn.dao.UserDao;
import com.dreams.rj.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public User getUserById(String id){
        return userService.getUserById(id);
    }
}
