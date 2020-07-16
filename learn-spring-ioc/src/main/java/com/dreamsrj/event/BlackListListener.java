package com.dreamsrj.event;

import org.springframework.context.ApplicationListener;

//3. 创建一个Listener, 监听事件的发布,然后处理该事件
public class BlackListListener implements ApplicationListener<BlackListEvent> {

    private static final String notificationAddr = "blacklistListener@dreams.com";

    @Override
    public void onApplicationEvent(BlackListEvent event) {
        // business logic
        // send notification email
        System.out.println("send eamil to balcklist : " + event.getAddress());
        System.out.println("send notification email : " + notificationAddr);
    }
}
