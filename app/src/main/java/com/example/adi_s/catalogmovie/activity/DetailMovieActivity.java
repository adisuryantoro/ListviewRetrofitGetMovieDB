package com.example.adi_s.catalogmovie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adi_s.catalogmovie.R;
import com.example.adi_s.catalogmovie.network.Contans;


public class DetailMovieActivity extends AppCompatActivity {
    ImageView imageViewPoster;
    TextView textViewTitle, textViewPopularity, textViewOverview, textViewReleaseDate;
    Button buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        imageViewPoster = findViewById(R.id.imageView_activity_detail_movie_1_1_1);
        textViewTitle = findViewById(R.id.textView_activity_detail_movie_1_1_2);
        textViewPopularity = findViewById(R.id.textView_activity_detail_movie_1_1_3);
        textViewOverview = findViewById(R.id.textView_activity_detail_movie_1_1_4);
        textViewReleaseDate = findViewById(R.id.textView_activity_detail_movie_1_1_5);

        buttonBack = findViewById(R.id.button_activity_detail_movie_2);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailMovieActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        String poster = bundle.getString(Contans.POSTER_MOVIE);
        String titleMovie = bundle.getString(Contans.TITLE_MOVIE);
        String popularity = bundle.getString(Contans.POPULARITY);
        String overview = bundle.getString(Contans.OVERVIEM);
        String releaseDate = bundle.getString(Contans.RELEASE_DATE);

        Glide.with(this).load("http://image.tmdb.org/t/p/w185/" + poster).into(imageViewPoster);
        textViewTitle.setText(titleMovie);
        textViewPopularity.setText(popularity);
        textViewOverview.setText(overview);
        textViewReleaseDate.setText(releaseDate);
    }
}
