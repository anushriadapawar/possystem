package com.example.possystem.utils;

import com.example.possystem.models.CartItem;
import com.example.possystem.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static List<CartItem> cartItems = new ArrayList<>();

    public static void addItem(Product product) {
        for (CartItem item : cartItems) {
            if (item.getName().equals(product.getName())) {
                // For simplicity in this demo, we'll recreate with incremented quantity
                int newQty = item.getQuantity() + 1;
                cartItems.remove(item);
                cartItems.add(new CartItem(product.getName(), product.getPrice(), newQty));
                return;
            }
        }
        cartItems.add(new CartItem(product.getName(), product.getPrice(), 1));
    }

    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    public static void clearCart() {
        cartItems.clear();
    }
}