package com.idenchev.classes.lookupmethod.annotation;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class AbstractLookup implements UseNonSingletonInterface {
    @Lookup()
    public NonSingleton getNonSingleton() {
        return null;
    };

    public void doSomething() {
        System.out.println(getNonSingleton().getLyrics());
    }
}
