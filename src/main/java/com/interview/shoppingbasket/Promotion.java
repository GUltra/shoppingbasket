package com.interview.shoppingbasket;

public class Promotion {
    int type;
    String associatedProduct;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAssociatedProduct() {
        return associatedProduct;
    }

    public void setAssociatedProduct(String associatedProduct) {
        this.associatedProduct = associatedProduct;
    }

    public Promotion(int type, String associatedProduct) {
        this.type = type;
        this.associatedProduct = associatedProduct;
    }
}
