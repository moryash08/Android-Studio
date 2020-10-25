package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    EditText editText;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        MainActivity.notes.add(String.valueOf(editText.getText()));
        MainActivity.myAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes", MODE_PRIVATE);

        try {
            sharedPreferences.edit().putString("notes", ObjectSerializer.serialize(MainActivity.notes)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText = findViewById(R.id.noteEditText);

        editText.isFocused();

        Intent intent = getIntent();
        String defaultText = intent.getStringExtra("note");
        editText.setText(defaultText);

    }
}