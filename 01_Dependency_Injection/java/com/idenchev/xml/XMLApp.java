package com.idenchev.xml;

import com.idenchev.classes.alias.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class XMLApp {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

        Person p0 = (Person) ctx.getBean("person");
        Person p1 = (Person) ctx.getBean("person1");
        Person p2 = (Person) ctx.getBean("person2");
        Person p3 = (Person) ctx.getBean("person3");
        Person p4 = (Person) ctx.getBean("xmlBeanAliasForPerson");

        Map<String, Person> people = ctx.getBeansOfType(Person.class);

        System.out.println(p0 == p1);
        System.out.println(p1 == p2);
        System.out.println(p2 == p3);
        System.out.println(p3 == p4);
        System.out.println(people.size() == 1);
    }
}
