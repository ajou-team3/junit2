package com.ajou.team3.junit2.restaurantKiosk.service;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;
import com.ajou.team3.junit2.restaurantKiosk.repository.CustomerMockRepository;
import com.ajou.team3.junit2.restaurantKiosk.repository.MealMockRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerMockService {
    private final CustomerMockRepository customerMockRepository;
    private final MealMockRepository mealMockRepository;

    //    public CustomerMockService(CustomerMockRepository customerMockRepository) {
//        this.customerMockRepository = customerMockRepository;
//    }
    //
    private List<Meal> customerMealCart = new ArrayList<>();

    public CustomerMockService(CustomerMockRepository customerMockRepository, MealMockRepository mealMockRepository) {
        this.customerMockRepository = customerMockRepository;
        this.mealMockRepository = mealMockRepository;
    }

    public Meal orderMealByName(String mealName) { // lee yong jae
        Meal meal = mealMockRepository.orderMealByName(mealName);
        return meal;
    }

    public void addMealinCart(Meal meal) {  //lee yeon ju
        Meal addMeal = new Meal(meal.getMealName(), meal.getMealPrice(), meal.getMealRemaining());
        customerMockRepository.addMealinCart(addMeal);
        return;
    }

    public int getTotalPrice(List<Meal> meal) { //lee yeon ju
        int totalPrice = 0;
        for (int i = 0; i < meal.size(); i++) {
            totalPrice += meal.get(i).getMealPrice();
        }
        return totalPrice;
    }

    public int addMealToCart(String mealName) {
        Meal meal = mealMockRepository.getMealByName(mealName);

        if (meal == null) {
            throw new NullPointerException("존재하지 않는 음식입니다.");
        } else if (meal.getMealRemaining() == 0) {
            throw new IllegalArgumentException("재고가 없는 음식입니다.");
        } else {
            customerMealCart.add(meal);
            return getTotalCartPrice();
        }
    }

    public int getTotalCartPrice() {
        int totalPrice = 0;
        for (Meal meal : customerMealCart) {
            totalPrice += meal.getMealPrice();
        }
        return totalPrice;
    }

    public void orderMealsInCart() {
        if (getCustomerCartSize() > 0) {
            for (Meal meal : customerMealCart) {
                mealMockRepository.orderMealByName(meal.getMealName());
            }
        }
    }

    public int getCustomerCartSize() {
        return customerMealCart.size();
    }

}