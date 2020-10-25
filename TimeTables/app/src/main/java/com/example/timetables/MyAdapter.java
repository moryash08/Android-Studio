package com.example.timetables;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter {

    private ArrayList<String> list;

    MyAdapter(ArrayList<String> list)
    {
        this.list = list;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;

        MyViewHolder(TextView textView)
        {
            super(textView);

            this.textView = textView;
            //this.textView = textView.findViewById(R.id.recyclerView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder) {
                ((MyViewHolder) holder).textView.setText(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
