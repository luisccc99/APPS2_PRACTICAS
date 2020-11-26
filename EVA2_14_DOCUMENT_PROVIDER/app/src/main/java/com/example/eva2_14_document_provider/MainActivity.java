package com.example.eva2_14_document_provider;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editTextDatos;
    public static final int ABRIR_ARCHIVO = 100;
    public static final int GUARDAR_ARCHIVO = 200;
    Uri uriRes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextDatos = findViewById(R.id.edit_text_datos);

    }

    public void onClickAbrir(View view) {
        Intent abrirIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        abrirIntent.addCategory(Intent.CATEGORY_OPENABLE);
        abrirIntent.setType("text/plain");
        abrirIntent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriRes);
        startActivityForResult(abrirIntent, ABRIR_ARCHIVO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ABRIR_ARCHIVO:
                if (resultCode == Activity.RESULT_OK) {
                    uriRes = data.getData();
                    String archivoText;
                    try {
                        InputStream is = getContentResolver().openInputStream(uriRes);
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        while ((archivoText = br.readLine()) != null) {
                            editTextDatos.append(archivoText);
                            editTextDatos.append("\n");
                        }
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case GUARDAR_ARCHIVO:
                if (resultCode == Activity.RESULT_OK) {
                    uriRes = data.getData();
                    String[] datos = editTextDatos.getText().toString().split("\n");
                    try {
                        OutputStream os = getContentResolver().openOutputStream(uriRes);
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        BufferedWriter brw = new BufferedWriter(osw);

                        for (int i = 0; i < datos.length; i++) {
                            brw.append(datos[i]);
                            brw.append("\n");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
            default:
                break;
        }
    }

    public void onClickGuardar(View view) {
        Intent guardarIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        guardarIntent.addCategory(Intent.CATEGORY_OPENABLE);
        guardarIntent.setType("text/plain");
        guardarIntent.putExtra(Intent.EXTRA_TITLE, "prueba.txt"); //guardar
        guardarIntent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriRes);
        startActivityForResult(guardarIntent, GUARDAR_ARCHIVO);
    }
}