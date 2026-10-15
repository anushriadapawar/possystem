package com.example.possystem.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.possystem.R;
import com.example.possystem.adapters.ProductAdapter;
import com.example.possystem.database.DBHelper;
import com.example.possystem.models.Product;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Product> list;
    DBHelper dbHelper;
    Button btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        listView = findViewById(R.id.listView);
        btnCart = findViewById(R.id.btnCart);

        list = new ArrayList<>();
        dbHelper = new DBHelper(this);

        loadProductsFromDatabase();

        btnCart.setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class))
        );
    }

    private void loadProductsFromDatabase() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM products", null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                list.add(new Product(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2)
                ));
            }
            cursor.close();
        }

        ProductAdapter adapter = new ProductAdapter(this, list);
        listView.setAdapter(adapter);
    }
}