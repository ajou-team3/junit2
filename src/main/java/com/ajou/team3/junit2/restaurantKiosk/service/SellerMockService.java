package com.ajou.team3.junit2.restaurantKiosk.service;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;
import com.ajou.team3.junit2.restaurantKiosk.repository.MealMockRepository;
import com.ajou.team3.junit2.restaurantKiosk.repository.SellerMockRepository;

import java.util.List;

public class SellerMockService {
    private final SellerMockRepository sellerMockRepository;
    private final MealMockRepository mealMockRepository;

    public SellerMockService(SellerMockRepository sellerMockRepository, MealMockRepository mealMockRepository) {
        this.sellerMockRepository = sellerMockRepository;
        this.mealMockRepository = mealMockRepository;
    }

    public List<Meal> findAllMeal() { // lee yong jae
        return mealMockRepository.findAllMeal();
    }

    public Meal getMealByName(String mealName){ //lee won woo
        Meal meals=mealMockRepository.getMealByName(mealName);
        return meals;
    }

     public Meal updateMealByName(String mealName,int newMealPrice, int newMealRemaining){ //lee won woo
        Meal meal =mealMockRepository.getMealByName(mealName);

        if(meal == null){
            throw new NullPointerException("등록되지 않은 음식입니다");
        } else {
            meal.setMealRemaining(newMealRemaining);
            meal.setMealPrice(newMealPrice);
            return meal;
        }
     }
}
