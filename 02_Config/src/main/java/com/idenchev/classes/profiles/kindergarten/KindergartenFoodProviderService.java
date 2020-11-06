package com.idenchev.classes.profiles.kindergarten;

import com.idenchev.classes.profiles.Food;
import com.idenchev.classes.profiles.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class KindergartenFoodProviderService implements FoodProviderService {
    @Override
    public List<Food> provideLunchSet() {
        List<Food> f = new ArrayList<>();
        f.add(new Food("Qica"));
        f.add(new Food("Mlqko"));
        f.add(new Food("Orehi"));
        return f;
    }
}
