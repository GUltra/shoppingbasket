package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
            BasketItem basketItem = new BasketItem();
            basketItem.setProductCode(productCode);
            basketItem.setProductName(productName);
            basketItem.setQuantity(quantity);

            items.add(basketItem);
    }

   /* public boolean consolidateItems(String productCode, int quantity) {

        Optional<BasketItem> newItems = items.stream().filter(x -> x.getProductCode().equals(productCode)).collect(Collectors.toList()).stream().findFirst();
        if(newItems.isPresent()) {
            newItems.get().setQuantity(newItems.get().getQuantity() + quantity);
            return false;
        }
        return true;
    }*/
    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
        // Exercise - implement this function
        int i = 0;
        while(i< items.size()){
            int finalI = i;
            int quantity = 0;
            List<BasketItem> newItems = items.stream().filter(x -> x.getProductCode().equals(items.get(finalI).getProductCode())).collect(Collectors.toList());
            for(BasketItem bi: newItems) {
                quantity += bi.getQuantity();
                if(quantity != bi.getQuantity()) {
                    items.remove(bi);
                }
            }
            Optional<BasketItem> optionalBasketItem = items.stream().filter(x -> x.getProductCode().equals(items.get(finalI).getProductCode())).findFirst();
            if(optionalBasketItem.isPresent()){
                optionalBasketItem.get().setQuantity(quantity);
            }
            i++;
        }
    }
}
