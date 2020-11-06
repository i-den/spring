package com.idenchev.classes;

import com.idenchev.classes.profiles.Food;
import com.idenchev.classes.profiles.FoodProviderService;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class ProfileApp {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "kindergarten");
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("profiles-beans.xml");

        FoodProviderService service = ctx.getBean("foodProviderService", FoodProviderService.class);

        for (Food f : service.provideLunchSet()) {
            System.out.println("Food: " + f.getName());
        }
    }
}
