package com.dreamsrj.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.util.List;

// 2.创建一个类实现 ApplicationEventPublisherAware 接口, 该类用于发布区事件
public class EmailService implements ApplicationEventPublisherAware {
    private List<String> blackList;
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = publisher;
    }

    public void sendEmail(String address, String context) {
        //check blackList
        if (blackList.contains(address)) {
            publisher.publishEvent(new BlackListEvent(this, address, context));
            return;
        }

        // send email
        System.out.println("send email to  " + address);
    }
}
