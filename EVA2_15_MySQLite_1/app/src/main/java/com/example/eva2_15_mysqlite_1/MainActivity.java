package com.example.eva2_15_mysqlite_1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteDatabase = openOrCreateDatabase("myData", MODE_PRIVATE, null);
        try {
            sqLiteDatabase.execSQL("CREATE TABLE animals(id integer primary key autoincrement, " +
                    "columna1 text, " +
                    "columna2 integer);");
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        try {
            sqLiteDatabase.execSQL("INSERT INTO prueba(columna1, columna2) VALUES('mmm', 32)");
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

    }
}