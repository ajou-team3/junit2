package com.ajou.team3.junit2.restaurantKiosk.repository;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;

import java.util.List;

public interface sellerRepository {
    public Meal getFoodByName(String mealName);
    public Meal updateFoodByName(String mealName);
    public List<Meal> findAllMeal();
}
