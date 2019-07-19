package com.ajou.team3.junit2.restaurantKiosk.domain;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Meal {
    private String mealName;
    private int mealPrice;
    private int mealRemaining;
}
