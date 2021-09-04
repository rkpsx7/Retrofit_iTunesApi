package com.example.retrofit_itunesapi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class viewHolder extends RecyclerView.ViewHolder {
    private ImageView ivCover, ivBtnPlay, ivBtnPause, ivBtnDelete;
    private TextView tvSongName, tvArtistName;
    private ClickListener clickListener;

    public viewHolder(@NonNull View itemView, ClickListener clickListener) {
        super(itemView);
        this.clickListener = clickListener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        ivCover = itemView.findViewById(R.id.ivCoverImage);
        tvSongName = itemView.findViewById(R.id.tvSongName);
        tvArtistName = itemView.findViewById(R.id.tvArtistName);
        ivBtnPlay = itemView.findViewById(R.id.ivBtnPlay);
        ivBtnPause = itemView.findViewById(R.id.ivBtnPause);
        ivBtnDelete = itemView.findViewById(R.id.ivBtnDelete);
    }

    public void setData(ResultsItem song) {
        Glide.with(ivCover).load(song.getArtworkUrl100()).into(ivCover);
        tvSongName.setText(song.getTrackName());
        tvArtistName.setText(song.getArtistName());


        ivBtnPlay.setOnClickListener(v -> {
            clickListener.onPlayButtonClicked(song.getPreviewUrl());
        });

        ivBtnPause.setOnClickListener(v -> {
            clickListener.onPauseButtonClicked();
        });

        ivBtnDelete.setOnClickListener(v -> {
            clickListener.onDeleteButtonClicked(song);
        });
    }
}
