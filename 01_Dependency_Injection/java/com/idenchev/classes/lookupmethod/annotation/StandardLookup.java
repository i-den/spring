package com.idenchev.classes.lookupmethod.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StandardLookup implements UseNonSingletonInterface {
    private NonSingleton nonSingleton;

    @Autowired
    public void setNonSingleton(NonSingleton nonSingleton) {
        this.nonSingleton = nonSingleton;
    }

    public NonSingleton getNonSingleton() {
        return nonSingleton;
    }

    public void doSomething() {
        System.out.println(nonSingleton.getLyrics());
    }
}
