package com.idenchev.classconfig;

import com.idenchev.classes.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Person person() {
        Person p = new Person();
        p.setAge(0);
        p.setName("Name");
        return p;
    }
}
