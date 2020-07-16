package com.dreams.rj.learn.properties;

import org.springframework.core.env.PropertySource;

import java.util.Map;

public class MyPropertySource extends PropertySource<Map<String,String>> {


    public MyPropertySource(String name, Map<String, String> source) {
        super(name, source);
    }

    @Override
    public Object getProperty(String name) {
        return this.source.get(name);
    }
}
