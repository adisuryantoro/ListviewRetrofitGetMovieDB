package com.example.adi_s.catalogmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adi_s.catalogmovie.R;
import com.example.adi_s.catalogmovie.activity.DetailMovieActivity;
import com.example.adi_s.catalogmovie.model.Result;
import com.example.adi_s.catalogmovie.network.Contans;

import java.util.List;

public class AdapterListMovie extends BaseAdapter{
    private Context mContext;
    private List<Result> dataResults;

    public AdapterListMovie(Context mContext, List<Result> results){
        this.mContext = mContext;
        this.dataResults = results;

    }

    @Override
    public int getCount() {
        return dataResults.size();
    }

    @Override
    public Object getItem(int position) {
        return dataResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.main_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            viewHolder.textViewTitle = convertView.findViewById(R.id.textView_main_item_3_2);
            viewHolder.textViewPopularity = convertView.findViewById(R.id.textView_main_item_3_3);
            //viewHolder.textViewOverview = convertView.findViewById(R.id.textView_activity_detail_movie_1_1_4);
            viewHolder.textViewRelease = convertView.findViewById(R.id.textView_main_item_3_4);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String linkPoster = dataResults.get(position).getPosterPath();
        Glide.with(mContext)
                .load("http://image.tmdb.org/t/p/w185/" + linkPoster)
                .into(viewHolder.imageViewPoster);

        viewHolder.textViewTitle.setText(dataResults.get(position).getTitle());
        viewHolder.textViewPopularity.setText(dataResults.get(position).getPopularity()+"");
        viewHolder.textViewRelease.setText(dataResults.get(position).getReleaseDate());


        viewHolder.cardViewMainItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Contans.POSTER_MOVIE, dataResults.get(position).getPosterPath());
                bundle.putString(Contans.TITLE_MOVIE, dataResults.get(position).getTitle());
                bundle.putString(Contans.POPULARITY, dataResults.get(position).getPopularity()+"");
                bundle.putString(Contans.OVERVIEM, dataResults.get(position).getOverview());
                bundle.putString(Contans.RELEASE_DATE, dataResults.get(position).getReleaseDate());

                Intent intent = new Intent(mContext, DetailMovieActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });


        return convertView;
    }

    private static class ViewHolder{
        ImageView imageViewPoster;
        TextView textViewTitle;
        TextView textViewPopularity;
        TextView textViewRelease;
        CardView cardViewMainItem;

        public ViewHolder(View view) {

            imageViewPoster = view.findViewById(R.id.imageView_main_item_3_1);
            textViewTitle = view.findViewById(R.id.textView_main_item_3_2);
            textViewPopularity = view.findViewById(R.id.textView_main_item_3_3);
            textViewRelease = view.findViewById(R.id.textView_main_item_3_4);
            cardViewMainItem = view.findViewById(R.id.cardView_main_item_3);
        }
    }

//    // Filter Class
//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        this.main.itemsModel.clear();
//        if (charText.length() == 0) {
//            this.main.itemsModel.addAll(arraylistitem);
//        }
//        else
//        {
//            for (ModelItems wp : arraylistitem)
//            {
//                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText))
//                {
//                    this.main.itemsModel.add(wp);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
}
