package com.idenchev.classes.lookupmethod.xml;

public class StandardLookup implements UseNonSingletonInterface {
    private NonSingleton nonSingleton;

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
