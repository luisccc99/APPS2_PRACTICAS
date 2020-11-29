package com.example.eva2_17_mysqlite_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewDatos;
    SQLiteDatabase sqLiteDatabase;
    public static final String DB_NAME = "mi_bd";
    List<String> nombres = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewDatos = findViewById(R.id.list_view_datos);
        sqLiteDatabase = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);

        try {
            sqLiteDatabase.execSQL("CREATE TABLE mitable(id integer PRIMARY KEY AUTOINCREMENT," +
                    "nombre text," +
                    "apellido text); ");
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        // insert
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", "LUIS CARLOS");
        contentValues.put("apellido", "CRUZ CASTILLO");
        sqLiteDatabase.insert("mitabla", null, contentValues);
        contentValues.clear();
        contentValues.put("nombre", "JESUS MARTIM");

        contentValues.put("apellido", "MORALES ALONSO");
        sqLiteDatabase.insert("mitabla", null, contentValues);
        contentValues.clear();
        contentValues.put("nombre", "MIGUEL ANGEL");

        long iClave;
        contentValues.put("apellido", "VALDEZ RODARTE");
        iClave = sqLiteDatabase.insert("mitabla", null, contentValues);
        contentValues.clear();

        Toast.makeText(this, "Clave " + iClave, Toast.LENGTH_SHORT).show();

        // update
        contentValues.clear();
        contentValues.put("nombre", "Uff");
        String[] args = {iClave + ""};
        sqLiteDatabase.update("mitabla", contentValues, "id = ?", args);

        // delete
        String[] args2 = {"PEDRO"};
        sqLiteDatabase.delete("mitabla", "nombre = ?", args2);


        //Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM mitabla", null);
        String[] columns = {"id", "nombre", "apellido"};
        String[] args3 = {"LUIS"};
        Cursor cursor = sqLiteDatabase.query(
                "mitabla", columns, "nombre LIKE ?"
                , args3, null, null, "apellido");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            nombres.add(
                    cursor.getString(cursor.getColumnIndex("id")) +
                            cursor.getString(cursor.getColumnIndex("nombre")) +
                            cursor.getString(cursor.getColumnIndex("apellido")));
            cursor.moveToNext();
        }

        listViewDatos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres));

    }
}