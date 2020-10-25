package com.example.phrases;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void onClick(View view)
    {
        Button button = (Button) view;
        String tagName = button.getTag().toString();

        MediaPlayer mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(tagName, "raw", getPackageName()));
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
