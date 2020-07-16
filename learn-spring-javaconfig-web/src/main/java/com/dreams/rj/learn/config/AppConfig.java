package com.dreams.rj.learn.config;

import com.dreams.rj.learn.bean.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource(name = "", value = "",encoding = "")
public class AppConfig {

    @Autowired
    private ApplicationContext context;

    @Bean(name = "testBean")
    public TestBean test() {
        TestBean bean = new TestBean();
        bean.setAge(1);
        bean.setName("abc");

        if (context == null) {
            System.out.println("content is null");
        } else {
            String[] names = context.getBeanDefinitionNames();
            for (String name : names) {
                System.out.println("register Bean : " + name);
            }
        }

        return bean;

    }

    @Bean
    @Profile("default")
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();
        config.setLocations(new ClassPathResource("test.properties"));
        return config;
    }

}
