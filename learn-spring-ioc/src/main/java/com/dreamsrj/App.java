package com.dreamsrj;

import com.dreamsrj.controller.PersonController;
import com.dreamsrj.service.UserService;
import com.dreamsrj.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("测试开始-----------------------------1");
        //通过加载配置文件实例化容器
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        System.out.println("容器初始化完成------------------------2");
//        //获取配置文件中定义的bean
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames));

        System.out.println("获取PersonController实例对象----------4");
        //第一次从容器中获取实例
        PersonController personController = context.getBean(PersonController.class);
        personController.getPersion();
        System.out.println("获取PersonController实例对象----------5");
        //第二次从容器中获取实例
        PersonController personController2 = context.getBean(PersonController.class);
        personController.getPersion();

        System.out.println("获取 UserService 实例对象(原型)----------6");
        UserService userService = context.getBean(UserServiceImpl.class);

        //显示true， 容器中的bean默认为单例。
        System.out.println("get twice person controller is equeal : " + (personController == personController2));
    }

}
