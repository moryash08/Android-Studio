package com.example.timerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("CountDown", Integer.toString( (int)(millisUntilFinished / 1000)) );
            }

            @Override
            public void onFinish() {
                Log.i("Finished", "CountDown Over.");
            }
        }.start();

        /*
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i("Hey!", "A Second has Passed.");

                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);
        */
    }
}
