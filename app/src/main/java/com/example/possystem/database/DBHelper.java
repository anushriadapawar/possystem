package com.example.possystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "POS_DB";
    private static final int DB_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS = "CREATE TABLE products(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "price REAL)";
        db.execSQL(CREATE_PRODUCTS);

        // Seed data for immediate functionality
        db.execSQL("INSERT INTO products (name, price) VALUES ('High-End Laptop', 75000.00)");
        db.execSQL("INSERT INTO products (name, price) VALUES ('Smart Watch', 15000.00)");
        db.execSQL("INSERT INTO products (name, price) VALUES ('Wireless Earbuds', 5000.00)");
        db.execSQL("INSERT INTO products (name, price) VALUES ('Mechanical Keyboard', 8000.00)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }
}