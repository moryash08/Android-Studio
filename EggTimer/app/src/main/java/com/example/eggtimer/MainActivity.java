package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    CountDownTimer countDownTimer;
    Button button;
    boolean flag = true;
    int max = 300;

    public void setTimer(int timer)
    {
        DecimalFormat formatter = new DecimalFormat("00");
        int sec = timer % 60;
        int min = timer / 60;

        String str = formatter.format(min) + " : " + formatter.format(sec);

        textView.setText(str);
    }

    @SuppressLint("SetTextI18n")
    public void Start(View view)
    {

        button = findViewById(R.id.startButton);
        button.setText("STOP");

        countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                setTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                Log.i("CountDown Over", "Finished");
                MediaPlayer mediaPlayer;
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.countdown_finish);

                mediaPlayer.start();
                setTimer(max / 2);
                seekBar.setProgress(max / 2);
                button.setText("START");
                flag = true;

            }
        }.start();

        flag = false;
    }

    @SuppressLint("SetTextI18n")
    public void Stop(View view)
    {
        countDownTimer.cancel();
        flag = true;
        button.setText("START");
    }

    public void onClickHandler(View view)
    {
        if(flag)
            Start(view);
        else
            Stop(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(max);
        seekBar.setProgress(max / 2);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textView = findViewById(R.id.textView);
        setTimer(max / 2);
    }
}
