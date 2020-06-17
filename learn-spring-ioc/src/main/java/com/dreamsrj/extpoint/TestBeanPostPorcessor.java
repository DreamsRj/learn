package com.dreamsrj.extpoint;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * BeanPostProcessor 按容器划分作用域
 *
 */
public class TestBeanPostPorcessor implements BeanPostProcessor , Ordered {
    @Autowired()
    public TestBeanPostPorcessor() {
    }

    /**
     * 后置处理, 在初始化之前
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    /**
     * 后置处理, 在初始化之后
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
