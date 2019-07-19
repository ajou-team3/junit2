package com.ajou.team3.junit2.restaurantKiosk.service;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;
import com.ajou.team3.junit2.restaurantKiosk.repository.CustomerMockRepository;

import java.util.List;

public class CustomerMockService {
    private final CustomerMockRepository customerMockRepository;

    public CustomerMockService(CustomerMockRepository customerMockRepository) {
        this.customerMockRepository = customerMockRepository;
    }

    
}
