package com.example.listview;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{

    private ArrayList<String> dogBreeds;
        static class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView textView;

            MyViewHolder(TextView textView) {
                super(textView);
                this.textView = textView;
            }
        }

        MyAdapter(ArrayList<String> dogBreeds)
        {
            this.dogBreeds = dogBreeds;
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {

            TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_activated_1, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(textView);
            return myViewHolder;

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.textView.setText(dogBreeds.get(position));
        }

        @Override
        public int getItemCount() {
            return dogBreeds.size();
        }
}