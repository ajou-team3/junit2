package com.ajou.team3.junit2.restaurantKiosk;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;
import com.ajou.team3.junit2.restaurantKiosk.repository.MealMockRepository;
import com.ajou.team3.junit2.restaurantKiosk.service.CustomerMockService;
import com.ajou.team3.junit2.restaurantKiosk.service.SellerMockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Junit2ApplicationTests {

    @Mock
    private MealMockRepository mealMockRepository;

    @InjectMocks
    private CustomerMockService customerMockService;
    @InjectMocks
    private SellerMockService sellerMockService;


    // 이영주
    @Test
    public void customerOrderMealByNameTest() {
        Meal meal = new Meal("시리얼", 3000, 5);
        when(customerMockService.orderMealByName("시리얼")).thenReturn(meal);
        int price = customerMockService.orderMealByName("시리얼").getMealPrice();
        assertThat(price, is(meal.getMealPrice()));
        verify(mealMockRepository, times(1)).orderMealByName(anyString());
    }

    @Test
    public void customerAddMealToCartTest() {
        when(mealMockRepository.getMealByName(anyString()))
                .thenReturn(new Meal("meal", 3000, 3));
        int totalPrice;
        totalPrice = customerMockService.addMealToCart(anyString());
        assertThat(totalPrice, is(3000));
        totalPrice = customerMockService.addMealToCart(anyString());
        assertThat(totalPrice, is(6000));
        assertThat(customerMockService.getCustomerCartSize(), is(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void customerAddRemainingZeroMealToCartTest() {
        Meal meal = mock(Meal.class);
        when(mealMockRepository.getMealByName(anyString())).thenReturn(meal);
        when(meal.getMealRemaining()).thenReturn(0);
        customerMockService.addMealToCart(anyString());
    }

    @Test(expected = NullPointerException.class)
    public void customerAddNonExistMealToCartTest() {
        given(mealMockRepository.getMealByName("없는 음식")).willThrow(new NullPointerException());
        customerMockService.addMealToCart("없는 음식");
    }

    @Test
    public void customerOrderEmptyCartTest() {
        customerMockService.orderMealsInCart();
        verify(mealMockRepository, times(0)).orderMealByName(anyString());
    }

    @Test
    public void customerOrderCartTest() {
        given(mealMockRepository.getMealByName(anyString()))
                .willReturn(new Meal("meal", 3000, 3));
        customerMockService.addMealToCart(anyString());
        customerMockService.addMealToCart(anyString());
        customerMockService.addMealToCart(anyString());

        customerMockService.orderMealsInCart();

        verify(mealMockRepository, atLeast(3)).orderMealByName(anyString());

    }


}
