package com.idenchev.classes.lookupmethod.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Configuration
@ComponentScan(basePackages = {"com.idenchev.classes.lookupmethod.annotation"})
public class AnnotationLookupApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationLookupApp.class);

        StandardLookup standardLookup = (StandardLookup) ctx.getBean("standardLookup");
        AbstractLookup abstractLookup = (AbstractLookup) ctx.getBean("abstractLookup");

        displayInfo("Standard Lookup", standardLookup);
        displayInfo("Abstract Lookup", abstractLookup);
    }
    private static void displayInfo(String lookupBeanName, UseNonSingletonInterface lookup) {
        NonSingleton n1 = lookup.getNonSingleton();
        NonSingleton n2 = lookup.getNonSingleton();

        System.out.println(lookupBeanName + ": same instances: " + (n1 == n2));

        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 0; i < 10000; i++) {
            NonSingleton n = lookup.getNonSingleton();
            n.getLyrics();
        }
        sw.stop();
        System.out.println("10000 instances took " + sw.getTotalTimeMillis());
    }
}
