package com.ajou.team3.junit2.restaurantKiosk.repository;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;

import java.util.List;

public interface SellerMockRepository {
    Meal getMealByName(String mealName);
    Meal updateMealByName(String mealName, int newMealPrice, int newMealRemaining);
    List<Meal> findAllMeal();
}