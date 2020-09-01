package com.example.rep_7_mediaplayer_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myIntent = new Intent(this, MyMusicPlayer.class);
    }

    public void playSong(View view) {
        startService(myIntent);
    }

    public void pauseSong(View view) {
        stopService(myIntent);
    }
}