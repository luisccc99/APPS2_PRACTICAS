package com.example.eva2_16_mysqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewDatos;
    SQLiteDatabase sqLiteDatabase;
    public static final String DB_NAME = "mi_base_datos";
    List<String> nombres = new ArrayList<>(50);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewDatos = findViewById(R.id.list_view_datos);
        sqLiteDatabase = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

        try {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE mitabla(id integer PRIMARY KEY AUTOINCREMENT, " +
                            "nombre text, " +
                            "apellido text);");
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        sqLiteDatabase.beginTransaction();

        try {
            sqLiteDatabase.execSQL("INSERT INTO mitabla(nombre, apellido) VALUES('RUBEN', 'HERNANDEZ')");
            sqLiteDatabase.execSQL("INSERT INTO mitabla(nombre, apellido) VALUES('LUIS', 'CRUZ')");
            sqLiteDatabase.execSQL("INSERT INTO mitabla(nombre, apellido) VALUES('MARTIN', 'MORALES')");
            sqLiteDatabase.execSQL("INSERT INTO mitabla(nombre, apellido) VALUES('MIGUEL', 'VALDEZ')");
            sqLiteDatabase.execSQL("INSERT INTO mitabla(nombre, apellido) VALUES('DAVID', 'HINOSTROZA')");
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.endTransaction();
        }

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM mitabla", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            nombres.add(
                    cursor.getString(cursor.getColumnIndex("nombre")) +
                            cursor.getString(cursor.getColumnIndex("apellido")));
            cursor.moveToNext();
        }

        listViewDatos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres));
    }
}