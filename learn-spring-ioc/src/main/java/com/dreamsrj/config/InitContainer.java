package com.dreamsrj.config;

import org.springframework.context.Lifecycle;

public class InitContainer implements Lifecycle {
    @Override
    public void start() {
        System.out.println("Calling Lifecycle Interface start() callback.");
    }

    @Override
    public void stop() {
        System.out.println("Calling Lifecycle Interface stop() callback.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
