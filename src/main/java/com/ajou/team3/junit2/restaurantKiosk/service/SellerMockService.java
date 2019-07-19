package com.ajou.team3.junit2.restaurantKiosk.service;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;
import com.ajou.team3.junit2.restaurantKiosk.repository.SellerMockRepository;

import java.util.List;

public class SellerMockService {
    private final SellerMockRepository sellerMockRepository

    public SellerMockService(SellerMockRepository sellerMockRepository) {
        this.sellerMockRepository = sellerMockRepository;
    }

    public List<Meal> findAllMeal() {
        return sellerMockRepository.findAllMeal();
    }
}
