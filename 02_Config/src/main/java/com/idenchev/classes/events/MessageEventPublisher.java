package com.idenchev.classes.events;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageEventPublisher implements ApplicationContextAware {
    private ApplicationContext ctx;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public void publish(String msg) {
        ctx.publishEvent(new MessageEvent(this, msg));
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("event_beans.xml");

        MessageEventPublisher publisher = (MessageEventPublisher) ctx.getBean("publisher");
        publisher.publish("The first published message");
    }
}
