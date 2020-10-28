package com.idenchev.classes.lookupmethod.xml;

public abstract class AbstractLookup implements UseNonSingletonInterface {
    public abstract NonSingleton getNonSingleton();

    public void doSomething() {
        System.out.println(getNonSingleton().getLyrics());
    }
}
