package com.example.highorlow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int num;

    public void newNum(View view) {

        Random ran = new Random();
        num = ran.nextInt(100)+1;

    }
    public void guessNum(View view) {

        Log.i("Info", "Button Works.");
        Log.i("Number", Integer.toString(num));

        EditText guessText = findViewById(R.id.numText);
        int getNum = Integer.parseInt(guessText.getText().toString());
        if (getNum < num)
            Toast.makeText(this, "Go Higher!", Toast.LENGTH_SHORT).show();
        else if (getNum > num)
            Toast.makeText(this, "Go Lower!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "You Got It!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random ran = new Random();
        num = ran.nextInt(100)+1;
    }
}
