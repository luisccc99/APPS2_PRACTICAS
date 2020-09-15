package com.example.eva_1_comunicacion_frag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListFragment listFragment;
    DataFragment dataFragment;

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof ListFragment) {
            listFragment = (ListFragment) fragment;
        } else {
            dataFragment = (DataFragment) fragment;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMessageFromFragmentToMain(String sender, String param) {
        if ("LISTA".equals(sender)) {
            dataFragment.onMessageFromMainToFragment(param);
        } else if ("DATA".equals(sender)) {
            Toast.makeText(this, param, Toast.LENGTH_SHORT).show();
        }
    }
}