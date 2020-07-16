package com.dreams.rj.learn.test;

import com.dreams.rj.learn.config.AppConfig;
import com.dreams.rj.learn.config.DefaultConfig;
import com.dreams.rj.learn.config.DevelopmentConfig;
import com.dreams.rj.learn.config.ProductEnvConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestProfile {

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("development");
        context.register(DefaultConfig.class, DevelopmentConfig.class, ProductEnvConfig.class);
        context.register();
    }
}
