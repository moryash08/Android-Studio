package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void Converter(View view) {

        EditText convert = (EditText) findViewById(R.id.editText);

        String money = convert.getText().toString();
        double calc = Double.valueOf(money);
        double amount = calc*74.3;
        String display = String.valueOf(amount).toString();

        Toast.makeText(this, "\u20B9 "+money+" is \u0024"+display, Toast.LENGTH_LONG).show();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
