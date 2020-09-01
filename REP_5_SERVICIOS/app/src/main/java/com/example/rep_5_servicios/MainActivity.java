package com.example.rep_5_servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentService = new Intent(this, MyService.class);
        BroadcastReceiver br = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter("MI_SERVICIO");
        registerReceiver(br, filter);
    }

    public void startMyService(View view) {
        intentService.putExtra("DATO", "Hello world");
        startService(intentService);
    }

    public void stopMyService(View view) {
        stopService(intentService);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        private final String TAG = MyBroadcastReceiver.class.getSimpleName();

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "receive: Message");
        }
    }
}