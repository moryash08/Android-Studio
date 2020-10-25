package com.example.alertdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;

    public void setLanguage(String language) {

        textView.setText(language);
        sharedPreferences.edit().putString("language", language).apply();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.English:
                setLanguage("English");
                return true;
            case R.id.Spanish:
                setLanguage("Spanish");
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.example.alertdemo", MODE_PRIVATE);

        textView = findViewById(R.id.textView);

        String language = sharedPreferences.getString("language", "Error");

        if (language.equals("Error")) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Select Language")
                    .setMessage("Which language do you want to use?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("English");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("Spanish");
                        }
                    })
                    .show();
        }
        else {
            textView.setText(language);
        }
    }
}