package com.ajou.team3.junit2.restaurantKiosk.repository;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;
import java.util.List;

public interface CustomerMockRepository {
    Meal orderMealByName(String mealName);
    void addMealinCart(Meal meal);
    int getTotalPrice(List<Meal> meal);
}
