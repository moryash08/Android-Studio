package com.example.multipleactivitydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> names = new ArrayList<>();
        names.add("Yash");
        names.add("Deep");
        names.add("Jason");
        names.add("Chris");
        names.add("Shane");

        MyAdapter myAdapter = new MyAdapter(this, names);
        recyclerView.setAdapter(myAdapter);

    }
}