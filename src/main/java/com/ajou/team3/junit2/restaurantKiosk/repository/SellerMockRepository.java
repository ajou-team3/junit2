package com.ajou.team3.junit2.restaurantKiosk.repository;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;

import java.util.List;

public interface SellerMockRepository {
    public Meal getMealByName(String mealName);
    public Meal updateMealByName(String mealName,int newMealPrice, int newMealRemaining);
    public List<Meal> findAllMeal();

}
