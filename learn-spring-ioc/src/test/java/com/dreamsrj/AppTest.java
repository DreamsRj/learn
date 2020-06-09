package com.dreamsrj;

import static org.junit.Assert.assertTrue;

import com.dreamsrj.controller.PersonController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test() {
        //通过加载配置文件实例化容器
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //获取配置文件中定义的bean
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        //第一次从容器中获取实例
        PersonController personController = context.getBean(PersonController.class);
        personController.getPersion();
        //第二次从容器中获取实例
        PersonController personController2 = context.getBean(PersonController.class);
        personController.getPersion();

        //显示true， 容器中的bean默认为单例。
        System.out.println("get twice person controller is equeal : " + (personController == personController2));
    }


    @Test
    public void testInitAndDestroy() {
        System.out.println("Start --------------------------------");
        //通过加载配置文件实例化容器
        ApplicationContext context = new ClassPathXmlApplicationContext("lifecycle-callback.xml");
        System.out.println("End   --------------------------------");
    }
}
