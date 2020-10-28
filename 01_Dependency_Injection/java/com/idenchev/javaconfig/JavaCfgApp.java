package com.idenchev.javaconfig;

import com.idenchev.classes.JavaCfgStringConstructor;
import com.idenchev.classes.OutputAmplifier;
import com.idenchev.classes.alias.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaCfgApp {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaCfg.class);


    }
}
