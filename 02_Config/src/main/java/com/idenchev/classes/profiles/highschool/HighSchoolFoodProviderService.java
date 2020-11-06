package com.idenchev.classes.profiles.highschool;

import com.idenchev.classes.profiles.Food;
import com.idenchev.classes.profiles.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class HighSchoolFoodProviderService implements FoodProviderService {
    public List<Food> provideLunchSet() {
        List<Food> f = new ArrayList<>();
        f.add(new Food("Duner"));
        f.add(new Food("Pizza"));
        f.add(new Food("Bireni Fustyci"));
        return f;
    }
}
