package com.interview.shoppingbasket;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class RetailPriceCheckoutStepTest {

    PricingService pricingService;
    CheckoutContext checkoutContext;
    PromotionsService promotionsService;
    Basket basket;
    List<Promotion> promotionList;

    @BeforeEach
    void setup() {
        pricingService = Mockito.mock(PricingService.class);
        checkoutContext = Mockito.mock(CheckoutContext.class);
        promotionsService = Mockito.mock(PromotionsService.class);
        basket = new Basket();
        promotionList = new ArrayList<>();

        when(checkoutContext.getBasket()).thenReturn(basket);
    }

    @Test
    void setPriceZeroForEmptyBasket() {

        RetailPriceCheckoutStep retailPriceCheckoutStep = new RetailPriceCheckoutStep(pricingService, promotionsService);

        retailPriceCheckoutStep.execute(checkoutContext);

        Mockito.verify(checkoutContext).setRetailPriceTotal(0.0);
    }

    @Test
    void setPriceOfProductToBasketItem() {

        basket.add("product1", "myproduct1", 10);
        basket.add("product2", "myproduct2", 10);
        promotionList.add(new Promotion(1,"product1"));
        promotionList.add(new Promotion(2,"product2"));
        when(pricingService.getPrice("product1")).thenReturn(5.0);
        when(pricingService.getPrice("product2")).thenReturn(4.0);
        when(promotionsService.getPromotions(basket)).thenReturn(promotionList);
        RetailPriceCheckoutStep retailPriceCheckoutStep = new RetailPriceCheckoutStep(pricingService, promotionsService);

        retailPriceCheckoutStep.execute(checkoutContext);
        Mockito.verify(checkoutContext).setRetailPriceTotal(5*5+2*10);

    }



}
