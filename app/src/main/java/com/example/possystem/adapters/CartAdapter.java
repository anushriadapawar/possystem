package com.example.possystem.adapters;

import android.content.Context;
import android.view.*;
import android.widget.*;

import com.example.possystem.R;
import com.example.possystem.models.CartItem;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    Context context;
    List<CartItem> list;

    public CartAdapter(Context context, List<CartItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override public int getCount() { return list.size(); }
    @Override public Object getItem(int i) { return list.get(i); }
    @Override public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }

        TextView name = view.findViewById(R.id.tvName);
        TextView price = view.findViewById(R.id.tvPrice);

        CartItem item = list.get(i);
        name.setText(item.getName() + " x" + item.getQuantity());
        price.setText("₹ " + item.getTotalPrice());

        return view;
    }
}