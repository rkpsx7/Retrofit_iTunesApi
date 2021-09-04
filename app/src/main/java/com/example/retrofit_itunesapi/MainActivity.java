package com.example.retrofit_itunesapi;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ClickListener {
    private Adapter adapter;
    private ArrayList<ResultsItem> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EditText etSearchBar;
    private Button BtnSearch;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        etSearchBar = findViewById(R.id.etSearchBar);
        BtnSearch = findViewById(R.id.btnSearch);
        mediaPlayer = new MediaPlayer();

        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi();
            }

            private void callApi() {
                ApiService apiService = NetWork.getInstance().create(ApiService.class);
                apiService.getSongs(etSearchBar.getText().toString()).enqueue(new Callback<ResponseiTunes>() {
                    @Override
                    public void onResponse(Call<ResponseiTunes> call, Response<ResponseiTunes> response) {
                        dataList = (ArrayList<ResultsItem>) response.body().getResults();
                        setAdapter();
                    }


                    @Override
                    public void onFailure(Call<ResponseiTunes> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void setAdapter() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(dataList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPlayButtonClicked(String songUrl) {
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this, Uri.parse(songUrl));
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Override
    public void onPauseButtonClicked() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    public void onDeleteButtonClicked(ResultsItem resultsItem) {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
        dataList.remove(resultsItem);
        adapter.notifyDataSetChanged();
    }

}