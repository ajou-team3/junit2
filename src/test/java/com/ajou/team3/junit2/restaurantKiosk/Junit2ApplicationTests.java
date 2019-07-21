package com.ajou.team3.junit2.restaurantKiosk;

import com.ajou.team3.junit2.restaurantKiosk.domain.Meal;
import com.ajou.team3.junit2.restaurantKiosk.repository.MealMockRepository;
import com.ajou.team3.junit2.restaurantKiosk.service.CustomerMockService;
import com.ajou.team3.junit2.restaurantKiosk.service.SellerMockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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


    //박수린
    @Test
    public void sellerFindAllMealTet(){
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal("meal", 5000, 50));
        meals.add(new Meal("meal2", 500000, 4000));
        when(mealMockRepository.findAllMeal()).thenReturn(meals);

        assertThat(sellerMockService.findAllMeal().get(0).getMealName(), is("meal"));
        assertThat(sellerMockService.findAllMeal().get(1).getMealName(), is("meal2"));
    }

    @Test(expected = NullPointerException.class)
    public void sellerFindAllMealButNothingInListTest(){
        List<Meal> meals = new ArrayList<>();
        assertThat(meals.isEmpty(), is(true));
        when(mealMockRepository.findAllMeal()).thenReturn(meals);
        sellerMockService.findAllMeal();
    }

    @Test
    public void sellerGetMealByNameTest(){
        when(mealMockRepository.getMealByName(anyString())).thenReturn(new Meal("비빔밥", 6500, 30));
        assertThat(sellerMockService.getMealByName(anyString()).getMealName(), is("비빔밥"));
        assertThat(sellerMockService.getMealByName(anyString()).getMealPrice(), is(6500));
        assertThat(sellerMockService.getMealByName(anyString()).getMealRemaining(), is(30));
    }

    @Test(expected = NullPointerException.class)
    public void sellerGetExceptionWhenGetMealThatDoesNotExistTest(){
        when(mealMockRepository.getMealByName("doesNotExist")).thenReturn(null);
        sellerMockService.getMealByName("doesNotExist");
    }

    @Test
    public void sellerUpdateMealTest(){
        when(mealMockRepository.updateMealByName("update", 3000, 0))
                .thenReturn(new Meal("update", 5000, 30));
        assertThat(sellerMockService.updateMealByName("update", 3000, 0).getMealName(), is("update"));
        assertThat(sellerMockService.updateMealByName("update", 3000, 0).getMealPrice(), is(5000));
        assertThat(sellerMockService.updateMealByName("update", 3000, 0).getMealRemaining(), is(30));
    }

    @Test(expected = NullPointerException.class)
    public void sellerGetExceptionWhenUpdateMealThatDoesNotExistTest(){
        doThrow(new NullPointerException()).when(mealMockRepository).getMealByName("doesNotExist");
        sellerMockService.updateMealByName("doesNotExist", 3000, 30);
    }
}
