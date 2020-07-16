package com.dreams.rj.learn.config;

import com.dreams.rj.learn.bean.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class ProductEnvConfig {
    @Bean
    public TestBean dataSource() {
        TestBean bean = new TestBean();
        bean.setName("production");
        return bean;
    }
}
