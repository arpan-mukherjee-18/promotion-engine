package org.arpanm18.exercise.dto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Cart {

    private final Map<SkUnit, Integer> cartItems;

    private Cart(Map<SkUnit, Integer> cartItems) {
        this.cartItems = cartItems;
    }

    public static Cart init() {
        return new Cart(new ConcurrentHashMap<>());
    }

    public Cart addItem(SkUnit item, int quantity) {
        this.cartItems.putIfAbsent(item, quantity);
        return this;
    }

    public Map<SkUnit, Integer> getCartItems() {
        return this.cartItems;
    }
}
