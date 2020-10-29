package com.example.eva2_9_transiciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, MainActivity2.class);
        img = findViewById(R.id.imageView);
    }

    public void onClickButton(View view) {
        ActivityOptions options = ActivityOptions
                .makeSceneTransitionAnimation(this, img, "my_img");
        startActivity(intent, options.toBundle());

    }
}