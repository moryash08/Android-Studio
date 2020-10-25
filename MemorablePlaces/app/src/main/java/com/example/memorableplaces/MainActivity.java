package com.example.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class MainActivity extends AppCompatActivity {

    static ArrayList<String> places = new ArrayList<>();
    static ArrayList<LatLng> mapLocations = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    static MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.memorableplaces", MODE_PRIVATE);

        ArrayList<String> latitudes = new ArrayList<>();
        ArrayList<String> longitudes = new ArrayList<>();

        places.clear();
        latitudes.clear();
        longitudes.clear();
        mapLocations.clear();

        try {

            places = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("places",ObjectSerializer.serialize(new ArrayList<String>())));
            latitudes = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("lats",ObjectSerializer.serialize(new ArrayList<String>())));
            longitudes = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("lons",ObjectSerializer.serialize(new ArrayList<String>())));

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (places.size() > 0 && latitudes.size() > 0 && longitudes.size() > 0) {
            if (places.size() == latitudes.size() && places.size() == longitudes.size()) {
                for (int i = 0; i < latitudes.size(); i++) {
                    mapLocations.add(new LatLng(Double.parseDouble(latitudes.get(i)), Double.parseDouble(longitudes.get(i))));
                }
            }
        } else {
            places.add("Add a new place...");
            mapLocations.add(new LatLng(0,0));
        }

        myAdapter = new MyAdapter(this, places);
        recyclerView.setAdapter(myAdapter);
    }

}