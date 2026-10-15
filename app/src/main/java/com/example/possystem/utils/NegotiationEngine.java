package com.example.possystem.utils;

import com.example.possystem.models.CartItem;
import java.util.List;

public class NegotiationEngine {

    public static double getFinalPrice(List<CartItem> cart) {

        double total = 0;
        int quantity = 0;

        for (CartItem item : cart) {
            total += item.getTotalPrice();
            quantity += item.getQuantity();
        }

        String tier = LoyaltyManager.getTier(total);
        double discount = 0;

        if (tier.equals("GOLD") && quantity >= 5) {
            discount = 0.15;
        } else if (tier.equals("SILVER") && quantity >= 3) {
            discount = 0.10;
        } else if (total > 3000) {
            discount = 0.05;
        }

        return total - (total * discount);
    }
}