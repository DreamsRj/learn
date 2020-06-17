package com.dreams.rj.learn;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAware {
    @Test
    public void testInitAndDestroy() {
        System.out.println("Start --------------------------------");
        //通过加载配置文件实例化容器
        ApplicationContext context = new ClassPathXmlApplicationContext("aware.xml");
        System.out.println("End   --------------------------------");
    }
}
