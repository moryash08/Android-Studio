package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class MainActivity extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static MyAdapter myAdapter;
    SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent = new Intent(this, SecondActivity.class);

        if ((item.getItemId()) == R.id.addNote) {
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        sharedPreferences = getSharedPreferences("com.example.notes", MODE_PRIVATE);

        notes.clear();

        try {
            notes = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("note", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (notes.isEmpty()) {
            notes.add("Example Note");
        }

        myAdapter = new MyAdapter(this, notes);
        recyclerView.setAdapter(myAdapter);

    }
}