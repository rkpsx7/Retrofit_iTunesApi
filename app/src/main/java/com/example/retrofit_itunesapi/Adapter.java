package com.example.retrofit_itunesapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<viewHolder> {
    private ArrayList<ResultsItem> dataList;
    private ClickListener clickListener;

    public Adapter(ArrayList<ResultsItem> dataList, ClickListener clickListener) {
        this.dataList = dataList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item_layout,parent,false);
        return new viewHolder(view,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ResultsItem song = dataList.get(position);
        holder.setData(song);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
