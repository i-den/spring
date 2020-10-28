package com.idenchev.classes.lookupmethod.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class NonSingleton {
    private String lyrics = "Bury all your secrets in my skin";

    public String getLyrics() {
        return lyrics;
    }
}
