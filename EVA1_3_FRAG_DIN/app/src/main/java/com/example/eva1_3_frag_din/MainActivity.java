package com.example.eva1_3_frag_din;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mostrarRojo(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        RedFragment red = new RedFragment();
        ft.replace(R.id.frame, red);
        ft.commit();
    }

    public void mostrarAzul(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BlueFragment blue = new BlueFragment();
        ft.replace(R.id.frame, blue);
        ft.commit();
    }
}