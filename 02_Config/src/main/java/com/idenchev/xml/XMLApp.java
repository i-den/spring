package com.idenchev.xml;

import com.idenchev.classes.Person;
import com.idenchev.classes.profiles.FoodProviderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.EventObject;

public class XMLApp {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("beans.xml");

        printBean("p1", ctx);
        printBean("p2", ctx);
        printBean("p3", ctx);

        ctx.registerShutdownHook();
    }

    private static void printBean(String beanName, ApplicationContext ctx) {
        Person p = (Person) ctx.getBean(beanName);
        System.out.println(p);
    }
}
