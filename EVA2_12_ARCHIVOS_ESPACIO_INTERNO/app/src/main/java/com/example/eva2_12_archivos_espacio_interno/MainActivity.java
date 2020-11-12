package com.example.eva2_12_archivos_espacio_interno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
    public final static String ARCHIVO = "mi_archivo.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextDatos = findViewById(R.id.edit_text_datos);
    }

    public void onClickGuardar(View view) {
        String[] datos = editTextDatos.getText().toString().split("\n");
        try {
            OutputStream os = openFileOutput(ARCHIVO, 0);
            OutputStreamWriter ow = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(ow);
            for (String dato : datos) {
                bw.append(dato);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onClickLeer(View view) {
        try {
            InputStream is = openFileInput(ARCHIVO);
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);
            String datos;
            while((datos = br.readLine()) != null) {
                editTextDatos.append(datos);
                editTextDatos.append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}