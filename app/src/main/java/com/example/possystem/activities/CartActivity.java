package com.example.possystem.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.possystem.R;
import com.example.possystem.adapters.CartAdapter;
import com.example.possystem.models.CartItem;
import com.example.possystem.utils.CartManager;
import com.example.possystem.utils.NegotiationEngine;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    ListView listView;
    TextView tvTotal;
    SwitchMaterial switchNegotiable;
    Button btnCheckout;
    CartAdapter adapter;
    List<CartItem> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = findViewById(R.id.listCart);
        tvTotal = findViewById(R.id.tvTotal);
        switchNegotiable = findViewById(R.id.switchNegotiable);
        btnCheckout = findViewById(R.id.btnCheckout);

        cartList = CartManager.getCartItems();
        adapter = new CartAdapter(this, cartList);
        listView.setAdapter(adapter);

        updateTotal();

        switchNegotiable.setOnCheckedChangeListener((btn, isChecked) -> {
            if (isChecked) {
                if (cartList.isEmpty()) {
                    Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
                    switchNegotiable.setChecked(false);
                    return;
                }
                runAgenticNegotiation();
            } else {
                updateTotal();
            }
        });

        btnCheckout.setOnClickListener(v -> {
            if (cartList.isEmpty()) {
                Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
            } else {
                new MaterialAlertDialogBuilder(this)
                        .setTitle("Order Placed")
                        .setMessage("Your order has been placed successfully!\nThank you for shopping with Vision POS.")
                        .setPositiveButton("OK", (d, w) -> {
                            CartManager.clearCart();
                            finish();
                        })
                        .show();
            }
        });
    }

    private void updateTotal() {
        double total = 0;
        for (CartItem item : cartList) {
            total += item.getTotalPrice();
        }
        tvTotal.setText(String.format(Locale.getDefault(), "Total: ₹ %.2f", total));
    }

    private void runAgenticNegotiation() {
        double originalTotal = 0;
        for (CartItem item : cartList) {
            originalTotal += item.getTotalPrice();
        }

        double negotiatedTotal = NegotiationEngine.getFinalPrice(cartList);

        if (negotiatedTotal < originalTotal) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("🤖 AI Negotiation Gate")
                    .setMessage(String.format(Locale.getDefault(),
                            "Customer AI Agent has negotiated a deal!\n\nOriginal: ₹ %.2f\nNegotiated: ₹ %.2f\n\nAccept this deal?",
                            originalTotal, negotiatedTotal))
                    .setPositiveButton("Accept Deal", (d, w) -> {
                        tvTotal.setText(String.format(Locale.getDefault(), "Final Negotiated: ₹ %.2f", negotiatedTotal));
                        Toast.makeText(this, "Negotiated price applied!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Reject", (d, w) -> {
                        switchNegotiable.setChecked(false);
                        updateTotal();
                    })
                    .setCancelable(false)
                    .show();
        } else {
            Toast.makeText(this, "AI Agent: No further discounts applicable.", Toast.LENGTH_LONG).show();
            switchNegotiable.setChecked(false);
        }
    }
}