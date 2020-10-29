package com.example.eva_10_preferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.settings_layout, new SettingsFragment());
        ft.commit();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Toast.makeText(this, sp.getString("nombre", "dick thunder")
                + "\n" + sp.getBoolean("dia_laboral", false)
                + "\n" + sp.getString("dia_semana", "Monday"), Toast.LENGTH_SHORT).show();
    }
}