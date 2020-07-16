package com.dreams.rj.learn;

import com.dreams.rj.learn.controller.UserController;
import com.dreams.rj.learn.dao.UserDao;
import com.dreams.rj.learn.dao.UserInfoDao;
import com.dreams.rj.learn.service.UserService;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class
App {
    public static void main(String[] args) {

//        configSpring();
//        configSpring2();
//        configSpring3();
        configSpring4();


    }

    /**
     * 在构成ApplicationContext 时将需要注入的组件注册到容器
     */
    public static void configSpring() {
        //注册组件
        ApplicationContext context = new AnnotationConfigApplicationContext(UserController.class, UserService.class, UserDao.class);

        UserController userController = context.getBean(UserController.class);
        User user = userController.getUserById("10001");
        System.out.println(user);
    }

    /**
     * 通过调用代册方法注册Bean
     */
    public static void configSpring2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //调用注册方法, 将每一个Bean都注册到容器
        context.register(UserController.class);
        context.register(UserService.class);
        context.register(UserDao.class);
        //必须调用该方法,将新注册的Bean更新到容器中
        context.refresh();

        UserController userController = context.getBean(UserController.class);
        User user = userController.getUserById("10001");
        System.out.println(user);

    }

    /**
     * 通过设置扫描包进行注册Bean
     */
    public static void configSpring3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //这里扫描了com.dreams.rj.learn这个包, 这个包下面的所有被注解的组件都可以被注册到容器中
        context.scan("com.dreams.rj.learn");
        context.refresh();

        UserController userController = context.getBean(UserController.class);
        User user = userController.getUserById("10001");
        System.out.println(user);

        UserInfoDao userinf = context.getBean(UserInfoDao.class);
        User user2 = userinf.findUser("1000");
        System.out.println(user2);
    }

    public static void configSpring4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //这里只注册了一个配置类, 但是配置类里面配置了其他的类, 所以他可以注册UserInfoDao
        context.register(SpringConfig.class);
        context.refresh();

        UserInfoDao userinf = context.getBean(UserInfoDao.class);
        User user = userinf.findUser("1000");
        System.out.println(user);
    }
}
