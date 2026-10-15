package com.example.possystem.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.possystem.R;
import com.example.possystem.database.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPrice;
    Button btnAdd, btnView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        dbHelper = new DBHelper(this);

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String priceStr = etPrice.getText().toString();

            if (name.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Please enter name and price", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double price = Double.parseDouble(priceStr);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("price", price);

                db.insert("products", null, values);
                etName.setText("");
                etPrice.setText("");
                Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid Price", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(v -> {
            startActivity(new Intent(this, ProductActivity.class));
        });
    }
}