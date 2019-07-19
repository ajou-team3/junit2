package com.ajou.team3.junit2.restaurantKiosk.repository;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;

import java.util.List;

public interface CustomerMockRepository {
    public Meal orderMealByName(String mealName);
    public void addMealinCart(Meal meal);
    public int getTotalPrice(List<Meal> meal);

}
