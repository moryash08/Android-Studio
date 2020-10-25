package com.example.audiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer media;
    AudioManager volumeControl;

    public void play(View view) {  media.start(); }

    public void pause(View view)
    {
        media.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volumeControl = (AudioManager) getSystemService(AUDIO_SERVICE);

        assert volumeControl != null;
        int maxVol = volumeControl.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVol = volumeControl.getStreamVolume(AudioManager.STREAM_MUSIC);

        media = MediaPlayer.create(this, R.raw.muscle_car);

        final SeekBar volumeBar = findViewById(R.id.volSeekBar);

        volumeBar.setMax(maxVol);
        volumeBar.setProgress(currentVol);

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.i("Volume Bar Moved", Integer.toString(progress));
                volumeControl.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar scrubBar = findViewById(R.id.scrubSeekBar);
        scrubBar.setMax(media.getDuration());

        scrubBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.i("Media Bar Moved", Integer.toString(progress));
                media.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubBar.setProgress(media.getCurrentPosition());
            }
        }, 0, 500);
    }
}
