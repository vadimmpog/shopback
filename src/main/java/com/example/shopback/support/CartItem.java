package com.example.shopback.support;

public class CartItem {
    public final String name;
    public final Float price;
    public final Integer quantity;
    public final Integer id;
    public final Integer productId;
    public CartItem(String name, Float price, Integer quantity, Integer id, Integer productId) {
        this.name = name;
        this.price = price;
        this.productId = productId;
        this.id = id;
        this.quantity = quantity;
    }
}