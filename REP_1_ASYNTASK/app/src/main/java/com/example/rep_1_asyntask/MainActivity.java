package com.example.rep_1_asyntask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    TextView textViewDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewDatos = findViewById(R.id.txt_view_datos);
        MiClaseAsincrona prueba = new MiClaseAsincrona();
        prueba.execute(1, 10, 1000);
    }

    class MiClaseAsincrona extends AsyncTask<Integer, String, Double> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textViewDatos.append("INICIO DE HILO\n");
        }

        @Override
        protected void onPostExecute(Double aDouble) {
            super.onPostExecute(aDouble);
            textViewDatos.append("FIN");
        }

        @Override
        protected Double doInBackground(@NotNull Integer... integers) {
            int i = integers[0];
            while (i < integers[1]) {
                try {
                    publishProgress("lorem ipsum\n");
                    Thread.sleep(integers[2]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(@NotNull String... values) {
            super.onProgressUpdate();
            textViewDatos.append(values[0]);
        }
    }
}