package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void clickFunction(View view) {

        EditText userName = (EditText) findViewById(R.id.userName);
        EditText password = (EditText) findViewById(R.id.password);

        Log.i("Username", userName.getText().toString());
        Log.i("Password", password.getText().toString());
        Toast.makeText(this, "Hello "+userName.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
