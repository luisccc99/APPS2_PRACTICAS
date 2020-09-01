package com.example.rep_3_runonuithread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textViewDatos;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            textViewDatos.append("Hello World\n");
        }
    };

    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run();
            int i = 0;
            while (i < 10) {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(runnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewDatos = findViewById(R.id.text_view_datos);
        thread.start();
    }
}