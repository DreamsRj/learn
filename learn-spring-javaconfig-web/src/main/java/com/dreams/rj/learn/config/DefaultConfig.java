package com.dreams.rj.learn.config;

import com.dreams.rj.learn.bean.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class DefaultConfig {
    @Bean
    public TestBean dataSource() {
        TestBean bean = new TestBean();
        bean.setName("default");
        return bean;
    }
}
