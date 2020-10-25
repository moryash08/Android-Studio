package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
        // sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Blake', 20)");
        // sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Chris', 24)");

        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM users", null);
        int userIndex = c.getColumnIndex("name");
        int ageIndex = c.getColumnIndex("age");
        c.moveToFirst();

        while (!c.isAfterLast()) {
            Log.i("Username", c.getString(userIndex));
            Log.i("Age", c.getString(ageIndex));
            c.moveToNext();
        }
        c.close();
    }
}