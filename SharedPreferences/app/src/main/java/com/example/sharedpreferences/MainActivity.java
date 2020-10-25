package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Saves data. After every launch data is not cleared.
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", MODE_PRIVATE);

//        ArrayList<String> friends = new ArrayList<>();
//        friends.add("Shawn");
//        friends.add("Jason");
//        friends.add("Chris");
//        friends.add("Zack");
//
//        try {
//            sharedPreferences.edit().putString("Friends", ObjectSerializer.serialize(friends)).apply();
//            Log.i("Friends Serialised", ObjectSerializer.serialize(friends));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        ArrayList<String> newFriends = new ArrayList<>();

        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("Friends", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("New Friends", String.valueOf(newFriends));
    }
}