package com.example.eva2_13_archivos_espacio_externo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editTextDatos;
    String rutaSDApp, rutaSD;
    boolean permisoArchivo;
    final static int PERMISO_ESCRITURA = 100;
    final static String ARCHIVO = "prueba.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextDatos = findViewById(R.id.edit_text_datos);
        rutaSD = Environment.getExternalStorageDirectory().getAbsolutePath();
        rutaSDApp = getExternalFilesDir(null).getAbsolutePath();
        Log.wtf("rutaSD", rutaSD);
        Log.wtf("rutaSDApp", rutaSDApp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISO_ESCRITURA);
            } else {
                permisoArchivo = true;
            }
        } else {
            permisoArchivo = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISO_ESCRITURA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permisoArchivo = true;
            }
        }
    }

    public void onClickGuardar(View view) {
        if (permisoArchivo) {
            String[] lines = editTextDatos.getText().toString().split("\n");
            try {
                FileOutputStream os = new FileOutputStream(rutaSDApp + "/" + ARCHIVO);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                for (int i = 0; i < lines.length; i++) {
                    bufferedWriter.append(lines[i]);
                    bufferedWriter.append("\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void onClickLeer(View view) {
        if (permisoArchivo) {
            String archivoText;
            try {
                FileInputStream fs = new FileInputStream(rutaSDApp + "/" + ARCHIVO);
                InputStreamReader ir = new InputStreamReader(fs);
                BufferedReader br = new BufferedReader(ir);
                while ((archivoText = br.readLine()) != null) {
                    editTextDatos.append(archivoText);
                    editTextDatos.append("\n");
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}