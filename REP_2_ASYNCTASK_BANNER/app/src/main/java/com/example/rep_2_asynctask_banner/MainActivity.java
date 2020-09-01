package com.example.rep_2_asynctask_banner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int[] images = {
            R.drawable.kimyojong1,
            R.drawable.kimyojong2,
            R.drawable.kimyojong3
    };

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageTask imageTask = new ImageTask();
        imageTask.execute(3000);
    }

    class ImageTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            imageView.setImageResource(values[0]);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int i = 0;
            while (i <= 2) {
                try {
                    Thread.sleep(integers[0]);
                    publishProgress(images[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i = i == 2 ? 0 : i + 1;
            }
            return null;
        }
    }
}