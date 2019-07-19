package com.ajou.team3.junit2.restaurantKiosk.service;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;
import com.ajou.team3.junit2.restaurantKiosk.repository.SellerMockRepository;

import java.util.List;

public class SellerMockService {
    private final SellerMockRepository sellerMockRepository;

    public SellerMockService(SellerMockRepository sellerMockRepository) {
        this.sellerMockRepository = sellerMockRepository;
    }

    public List<Meal> findAllMeal() { // lee yong jae
        return sellerMockRepository.findAllMeal();
    }

    public Meal getMealByName(String mealName){ //lee won woo
        Meal meals=sellerMockRepository.getMealByName(mealName);
        return meals;
    }

     public Meal updateMealByName(String mealName,int newMealPrice, int newMealRemaining){ //lee won woo
        Meal meal =sellerMockRepository.getMealByName(mealName);
        meal.setMealRemaining(newMealRemaining);
        meal.setMealPrice(newMealPrice);
        return meal;

     }
}
