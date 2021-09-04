package com.example.retrofit_itunesapi;

public interface ClickListener {
    void onPlayButtonClicked(String songUrl);
    void onPauseButtonClicked();
    void onDeleteButtonClicked(ResultsItem resultsItem);
}
