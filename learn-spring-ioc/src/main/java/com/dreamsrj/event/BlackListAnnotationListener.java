package com.dreamsrj.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class BlackListAnnotationListener {


    @EventListener
    public void processBlackListEvent(BlackListEvent event){
        //process BlacKListEvent
    }
}
