package com.example.newsreader;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    private ArrayList<String> titles;
    private ArrayList<String> content;

    MyAdapter(Context context, ArrayList<String> titles, ArrayList<String> content) {
        this.context = context;
        this.titles = titles;
        this.content = content;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(android.R.layout.simple_selectable_list_item, parent, false);
        return new MyViewHolder((TextView) view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final String contentText = content.get(position);
        holder.textView.setText(position+1+". "+titles.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoryActivity.class);
                intent.putExtra("content", contentText);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        MyViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }
}
