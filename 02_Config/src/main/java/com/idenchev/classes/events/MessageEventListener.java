package com.idenchev.classes.events;

import org.springframework.context.ApplicationListener;

public class MessageEventListener implements ApplicationListener<MessageEvent> {
    public void onApplicationEvent(MessageEvent event) {
        System.out.println("Received " + event.getMsg());
    }
}
