package com.dreams.rj.learn.test;

import com.dreams.rj.learn.properties.MyPropertySource;
import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;

public class TestProperties {
    @Test
    public void test(){
        GenericApplicationContext ctx = new GenericApplicationContext();
        ConfigurableEnvironment env = ctx.getEnvironment();
        boolean has = env.containsProperty("my-property");
        System.out.println("environment contains my-property ? " + has);
    }


    @Test
    public void test2(){
        GenericApplicationContext ctx = new GenericApplicationContext();
        ConfigurableEnvironment env = ctx.getEnvironment();
        MutablePropertySources sources = env.getPropertySources();
        MyPropertySource mySource = new MyPropertySource("built-in HashMap PropertySource", new HashMap<String, String>());
        sources.addFirst(mySource);
    }
}
