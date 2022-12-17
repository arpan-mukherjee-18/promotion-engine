package org.arpanm18.exercise;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Cart {

    private final Map<String, Integer> cartItems;

    private Cart(Map<String, Integer> cartItems) {
        this.cartItems = cartItems;
    }

    public static Cart init(){
        return new Cart(new ConcurrentHashMap<>());
    }

    public Cart addItem(String item, int quantity) {
        this.cartItems.putIfAbsent(item, quantity);
        return this;
    }

}
