package com.example.possystem.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.possystem.R;
import com.example.possystem.models.Product;

import java.util.ArrayList;

public class BillingActivity extends AppCompatActivity {

    TextView tvTotal;
    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        tvTotal = findViewById(R.id.tvTotal);

        // 🔴 TEMP DATA (replace later with real cart)
        productList = new ArrayList<>();
        productList.add(new Product(1, "Item 1", 100));
        productList.add(new Product(2, "Item 2", 200));

        calculateTotal();
    }

    private void calculateTotal() {
        double total = 0;

        for (Product p : productList) {
            if (p != null) {
                total += p.getPrice();
            }
        }

        tvTotal.setText("Total: ₹ " + total);
    }
}