package com.example.dogswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void imageSwitch(View view) {

        ImageView dogImage = (ImageView) findViewById(R.id.imageView);
        dogImage.setImageResource(R.drawable.dog1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
