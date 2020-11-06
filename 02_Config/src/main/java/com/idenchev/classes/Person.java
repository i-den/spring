package com.idenchev.classes;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PreDestroy;

public class Person implements ApplicationContextAware {
    private String name;
    private Integer age = -1;
    private String beanName;

    public Person() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public void init() {
        System.out.println("Initializing Bean Person " + name);
        if (name == null) {
            System.out.println("Name is not set");
        }
        if (age == -1) {
            System.out.println("Age is not set");
        }
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Calling from the @PreDestroy annotation for Bean with Name: " + name);
    }

    @Override
    public String toString() {
        return "Person {" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(applicationContext instanceof GenericApplicationContext) {
            ((GenericApplicationContext) applicationContext).registerShutdownHook();
        }
    }
}
