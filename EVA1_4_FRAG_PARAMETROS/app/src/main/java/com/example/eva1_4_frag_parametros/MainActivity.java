package com.example.eva1_4_frag_parametros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void crearFragment(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ParamFragment paramFragment = ParamFragment.newInstance("Science", "Bitch");
        ft.replace(R.id.frame_layout, paramFragment);
        ft.commit();
    }
}