package com.idenchev.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JavaCfgStringConstructor {
    private final String string;

    @Autowired
    public JavaCfgStringConstructor(@Value("This is injected through the @Value constructor annotation") String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
