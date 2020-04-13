package com.example.movie.MovieAdapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie.MovieModel.Item;
import com.example.movie.R;
import com.example.movie.ToTalHome.TotalHome.Main.sampleMovieActivity;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<Item> mMovieInfoArrayList;

    public MovieAdapter(Context context, ArrayList<Item> movieInfoArrayList) {
        this.mContext = context;
        this.mMovieInfoArrayList = movieInfoArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;

        Item item = mMovieInfoArrayList.get(position);


        movieViewHolder.mTvTitle.setText(Html.fromHtml(item.getTitle()));
        movieViewHolder.mRbUserRating.setRating(Float.parseFloat(item.getUserRating()) / 2);
        movieViewHolder.mTvPubData.setText(item.getPubDate());
        movieViewHolder.mTvDirector.setText(Html.fromHtml(item.getDirector()));
        movieViewHolder.mTvActor.setText(Html.fromHtml(item.getActor()));

        Glide.with(mContext)
                .load(item.getImage())
                .into(movieViewHolder.getImage());

        movieViewHolder.ll_movie_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, sampleMovieActivity.class);
                String movieTitle = item.getTitle().replace("<b>", "").replace("</b>", "");
                intent.putExtra("title", movieTitle);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMovieInfoArrayList.size();
    }

    public void clearItems() {
        mMovieInfoArrayList.clear();
        notifyDataSetChanged();
    }

    public void clearAndAddItems(ArrayList<Item> items) {
        mMovieInfoArrayList.clear();
        mMovieInfoArrayList.addAll(items);
        notifyDataSetChanged();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_movie_box;
        private ImageView mIvPoster;
        private TextView mTvTitle;
        private RatingBar mRbUserRating;
        private TextView mTvPubData;
        private TextView mTvDirector;
        private TextView mTvActor;

        MovieViewHolder(View view) {
            super(view);
            ll_movie_box = view.findViewById(R.id.ll_movie_box);
            mIvPoster = view.findViewById(R.id.iv_poster);
            mTvTitle = view.findViewById(R.id.tv_title);
            mRbUserRating = view.findViewById(R.id.rb_user_rating);
            mTvPubData = view.findViewById(R.id.tv_pub_data);
            mTvDirector = view.findViewById(R.id.tv_director);
            mTvActor = view.findViewById(R.id.tv_actor);
        }

        public ImageView getImage() {
            return mIvPoster;
        }

    }
}
