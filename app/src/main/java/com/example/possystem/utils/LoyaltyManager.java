package com.example.possystem.utils;

public class LoyaltyManager {

    public static String getTier(double total) {
        if (total > 5000) return "GOLD";
        if (total > 2000) return "SILVER";
        return "REGULAR";
    }
}