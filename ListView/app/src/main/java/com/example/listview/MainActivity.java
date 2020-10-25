package com.example.listview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ArrayList<String> dogBreeds = new ArrayList<>();
        dogBreeds.add("Golden Retriever");
        dogBreeds.add("Samoyed");
        dogBreeds.add("Beagle");
        dogBreeds.add("Siberian Husky");
        dogBreeds.add("Pit Bull");

        dogBreeds.add("Golden Retriever");
        dogBreeds.add("Samoyed");
        dogBreeds.add("Beagle");
        dogBreeds.add("Siberian Husky");
        dogBreeds.add("Pit Bull");

        dogBreeds.add("Golden Retriever");
        dogBreeds.add("Samoyed");
        dogBreeds.add("Beagle");
        dogBreeds.add("Siberian Husky");
        dogBreeds.add("Pit Bull");

        dogBreeds.add("Golden Retriever");
        dogBreeds.add("Samoyed");
        dogBreeds.add("Beagle");
        dogBreeds.add("Siberian Husky");
        dogBreeds.add("Pit Bull");

        dogBreeds.add("Golden Retriever");
        dogBreeds.add("Samoyed");
        dogBreeds.add("Beagle");
        dogBreeds.add("Siberian Husky");
        dogBreeds.add("Pit Bull");

        dogBreeds.add("Golden Retriever");
        dogBreeds.add("Samoyed");
        dogBreeds.add("Beagle");
        dogBreeds.add("Siberian Husky");
        dogBreeds.add("Pit Bull");

        dogBreeds.add("Golden Retriever");
        dogBreeds.add("Samoyed");
        dogBreeds.add("Beagle");
        dogBreeds.add("Siberian Husky");
        dogBreeds.add("Pit Bull");

        dogBreeds.add("Golden Retriever");
        dogBreeds.add("Samoyed");
        dogBreeds.add("Beagle");
        dogBreeds.add("Siberian Husky");
        dogBreeds.add("Pit Bull");
        //ArrayAdapter<String> dogAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , dogBreeds);

        MyAdapter myAdapter = new MyAdapter(dogBreeds);
        recyclerView.setAdapter(myAdapter);

    }
}
