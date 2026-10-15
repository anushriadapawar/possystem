package com.example.possystem.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;

import com.example.possystem.R;
import com.example.possystem.models.Product;
import com.example.possystem.utils.CartManager;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    Context context;
    List<Product> list;

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @Override public int getCount() { return list.size(); }
    @Override public Object getItem(int i) { return list.get(i); }
    @Override public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        TextView name = view.findViewById(R.id.tvName);
        TextView price = view.findViewById(R.id.tvPrice);
        Button btnAdd = view.findViewById(R.id.btnAddItem);

        Product p = list.get(i);
        name.setText(p.getName());
        price.setText("₹ " + p.getPrice());

        btnAdd.setOnClickListener(v -> {
            CartManager.addItem(p);
            Toast.makeText(context, p.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}