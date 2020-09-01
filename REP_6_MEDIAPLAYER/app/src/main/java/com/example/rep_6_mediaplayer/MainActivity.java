package com.example.rep_6_mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.dont_stop_me_now);
    }

    public void playSong(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void stopSong(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}