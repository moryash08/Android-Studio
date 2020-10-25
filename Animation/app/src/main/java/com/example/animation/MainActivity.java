package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view) {
        Log.i("Info", "ImageView Tapped");

        ImageView imgView = findViewById(R.id.imageView1);
        ImageView imgView2 = findViewById(R.id.imageView2);

        if (imgView.ALPHA.get(imgView) == 0) {
            imgView2.animate().alpha(0).setDuration(2000);
            imgView.animate().alpha(1).setDuration(2000);
        } else if (imgView.ALPHA.get(imgView) == 1) {
            imgView.animate().alpha(0).setDuration(2000);
            imgView2.animate().alpha(1).setDuration(2000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
