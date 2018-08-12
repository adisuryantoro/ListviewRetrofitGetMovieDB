package com.example.adi_s.catalogmovie.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adi_s.catalogmovie.R;
import com.example.adi_s.catalogmovie.adapter.AdapterListMovie;
import com.example.adi_s.catalogmovie.model.DataListMovie;
import com.example.adi_s.catalogmovie.model.Result;
import com.example.adi_s.catalogmovie.network.ApiMovieClient;
import com.example.adi_s.catalogmovie.network.ApiMovieInterface;
import com.example.adi_s.catalogmovie.network.Contans;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    ListView listViewMainActivity;
    ImageView imageViewPoster;
    TextView textViewTitle, textViewPopularity, textViewRelease;
    List<Result> mDataListMovie;
    AdapterListMovie adapterListMovie;
    SearchView searchViewMovie;

    private String MOVIE_LIST = "https://api.themoviedb.org/3/search/movie?api_key=ec067956bcd6ac32f62ff0a1a4828dfb&language=en-US&query=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataListMovie = new ArrayList<>();

        imageViewPoster = findViewById(R.id.imageView_main_item_3_1);
        textViewTitle = findViewById(R.id.textView_main_item_3_2);
        textViewPopularity = findViewById(R.id.textView_main_item_3_3);
        textViewRelease = findViewById(R.id.textView_main_item_3_4);
        listViewMainActivity = findViewById(R.id.listView_activity_main_3);
        searchViewMovie = findViewById(R.id.searchView_activity_main_1);


        doApiMovieCall();

        adapterListMovie = new AdapterListMovie(this, mDataListMovie);
        listViewMainActivity.setAdapter(adapterListMovie);

        listViewMainActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Result mResult = mDataListMovie.get(position);

                Intent intent = new Intent(MainActivity.this, DetailMovieActivity.class);
                startActivity(intent);

            }
        });

    }

    public void doApiMovieCall(){
        ApiMovieInterface apiMovieInterface = ApiMovieClient.createServiceClient(ApiMovieInterface.class);

        Call<DataListMovie> getNoticeListMovie = apiMovieInterface.getDataListMovie("popular", getString(R.string.api_key));
        getNoticeListMovie.enqueue(new Callback<DataListMovie>() {
            @Override
            public void onResponse(Call<DataListMovie> call, Response<DataListMovie> response) {
                if (response.body().getResults()!=null){
                    mDataListMovie.addAll(response.body().getResults());
                }
                adapterListMovie.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DataListMovie> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+ t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}