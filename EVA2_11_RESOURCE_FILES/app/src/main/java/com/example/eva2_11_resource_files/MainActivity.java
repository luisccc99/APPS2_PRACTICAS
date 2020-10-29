package com.example.eva2_11_resource_files;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView textViewArchivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewArchivo = findViewById(R.id.text_view_archivo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        InputStream is = getResources().openRawResource(R.raw.lorem_ipsum);
        InputStreamReader ir = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(ir);
        String lorem;
        try {
            while ((lorem = br.readLine()) != null) {
                textViewArchivo.append(lorem);
                textViewArchivo.append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}