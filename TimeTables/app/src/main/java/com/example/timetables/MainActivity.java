package com.example.timetables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.SeekBar;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(20);
        seekBar.setProgress(10);

        ArrayList<String> list = new ArrayList<>();

        for(int j = 1; j <= 21; j++)
            list.add(Integer.toString(seekBar.getProgress() * j));

        MyAdapter myAdapter = new MyAdapter(list);
        recyclerView.setAdapter(myAdapter);
        //generate(10);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int min = 1;
                int current;

                current = Math.max(progress, min);
                //seekBar.setProgress(current);

                ArrayList<String> list = new ArrayList<>();

                for(int j = 1; j <= 20; j++)
                    list.add(Integer.toString(j * current));

                MyAdapter myAdapter = new MyAdapter(list);
                recyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}
