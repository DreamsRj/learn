package com.dreamsrj.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtils implements ApplicationContextAware {
    private ApplicationContext context;

    public SpringContextUtils() {
        System.out.println("Create instance for SpringContextUtils ... ");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        System.out.printf("context is null ? %s%n", context.getClass().getName());
    }

    public Object getBean(String name) {
        return context.getBean(name);
    }

    public Object getBean(Class clasz) {
        return context.getBean(clasz);
    }
}
