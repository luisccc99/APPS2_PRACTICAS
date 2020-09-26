package com.example.rep_4_runonuithread_banner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int i = 0;
    int[] images = new int[]{
            R.drawable.cirlce,
            R.drawable.square,
            R.drawable.triangle
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            imageView.setImageResource(images[i]);
        }
    };
    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run();
            while (i <= 2) {
                try {
                    Thread.sleep(1500);
                    runOnUiThread(runnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i =  i == 2 ? 0 : i + 1;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        thread.start();
    }
}