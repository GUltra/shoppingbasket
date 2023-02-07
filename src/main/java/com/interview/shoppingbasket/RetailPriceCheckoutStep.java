package com.interview.shoppingbasket;

import java.util.List;
import java.util.Objects;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private PromotionsService promotionsService;
    private double retailTotal;

    public RetailPriceCheckoutStep(PricingService pricingService, PromotionsService promotionsService) {
        this.pricingService = pricingService;
        this.promotionsService = promotionsService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        retailTotal = 0.0;
        List<Promotion> promotionList = this.promotionsService.getPromotions(basket);
        for (BasketItem basketItem: basket.getItems()) {
            int quantity = basketItem.getQuantity();
            double price = pricingService.getPrice(basketItem.getProductCode());
            price = !promotionList.isEmpty() ? this.applyPromotion(promotionList.stream().filter(x -> Objects.equals(x.getAssociatedProduct(), basketItem.getProductCode())).findFirst().get(),basketItem, price) : price;
            basketItem.setProductRetailPrice(price);
            retailTotal += quantity*price;
        }

        checkoutContext.setRetailPriceTotal(retailTotal);
    }

    public double applyPromotion(Promotion promotion, BasketItem item, double price) {
        switch (promotion.getType()) {
            case 1:
                if(item.getQuantity() % 2 == 0) {
                    return price/2;
                }
                return price;
            case 2: return price/2;
            case 3: return price - price*0.10;
            default: return price;
        }
    }
}
