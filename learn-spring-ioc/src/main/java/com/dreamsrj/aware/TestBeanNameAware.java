package com.dreamsrj.aware;

import org.springframework.beans.factory.BeanNameAware;

public class TestBeanNameAware implements BeanNameAware {
    private String beanName;

    private String prop;

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
        System.out.println("set prop");
    }

    public TestBeanNameAware() {
        System.out.println("Create instance for TestBeanNameAware ...");
    }

    /**
     * 该方法在设置玩属性值之后, 调用, 在初始化方法之前调用
     *
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        beanName = s;
        System.out.println("Bean name is : " + beanName);
    }

    public void init() {
        System.out.println("init TestBeanNameAware ...");
    }

}
