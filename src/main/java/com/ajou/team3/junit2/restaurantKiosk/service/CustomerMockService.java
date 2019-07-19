package com.ajou.team3.junit2.restaurantKiosk.service;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;
import com.ajou.team3.junit2.restaurantKiosk.repository.CustomerMockRepository;

import java.util.List;

public class CustomerMockService {
    private final CustomerMockRepository customerMockRepository;

    public CustomerMockService(CustomerMockRepository customerMockRepository) {
        this.customerMockRepository = customerMockRepository;
    }

    public Meal orderMealByName(String mealName) { // lee yong jae
        Meal meal = customerMockRepository.orderMealByName(mealName);
        return meal;
    }

    public void addMealinCart(Meal meal){
        Meal addMeal = new Meal(meal.getMealName(), meal.getMealPrice(), meal.getMealRemaining());
        customerMockRepository.addMealinCart(addMeal);
        return;
    }
    public int getTotalPrice(List<Meal> meal){
        int totalPrice = 0;
        for(int i=0; i<meal.size(); i++){
            totalPrice += meal.get(i).getMealPrice();
        }
        return totalPrice;
    }

}
