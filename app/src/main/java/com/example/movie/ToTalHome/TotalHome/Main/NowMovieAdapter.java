package com.example.movie.ToTalHome.TotalHome.Main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie.R;

import java.util.ArrayList;

public class NowMovieAdapter extends RecyclerView.Adapter<NowMovieAdapter.ViewHolder> {

    private ArrayList<NowMovieItem> nowMovieList;
    Context context;

    public NowMovieAdapter(ArrayList<NowMovieItem> nowMovieList, Context context) {
        this.nowMovieList = nowMovieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.now_movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String movieTitle = nowMovieList.get(position).getTitle();
        Glide.with(holder.itemView)
                .load(nowMovieList.get(position).getImg_url())
                .into(holder.ivMovieImage);

        holder.clNowMovieBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, sampleMovieActivity.class);
                intent.putExtra("title", movieTitle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nowMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout clNowMovieBox;
        private ImageView ivMovieImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clNowMovieBox = itemView.findViewById(R.id.cl_now_movie_box);
            ivMovieImage = itemView.findViewById(R.id.iv_movie_image);
        }
    }
}
