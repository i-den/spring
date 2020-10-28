package com.idenchev.classes.lookupmethod.xml;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class XMLLookupApp {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("beans.xml");

        StandardLookup standardLookup = (StandardLookup) ctx.getBean("standardLookup");
        AbstractLookup abstractLookup = (AbstractLookup) ctx.getBean("abstractLookup");

        displayInfo("Standard Lookup", standardLookup);
        displayInfo("Abstract Lookup", abstractLookup);
    }

    /**
     * Standard Lookup: same instances: true
     * 10000 instances took 0
     * Abstract Lookup: same instances: false
     * 10000 instances took 63
     */
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
