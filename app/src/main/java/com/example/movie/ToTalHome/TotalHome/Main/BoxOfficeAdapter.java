package com.example.movie.ToTalHome.TotalHome.Main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;

import java.text.DecimalFormat;
import java.util.List;

public class BoxOfficeAdapter extends RecyclerView.Adapter<BoxOfficeAdapter.BoxOfficeViewHolder> {
    List<WeeklyBoxOfficeList> weeklyBoxOfficeLists;
    Context context;
    DecimalFormat formatter = new DecimalFormat("###,###");

    public BoxOfficeAdapter(List<WeeklyBoxOfficeList> weeklyBoxOfficeLists, Context context) {
        this.weeklyBoxOfficeLists = weeklyBoxOfficeLists;
        this.context = context;
    }

    @NonNull
    @Override
    public BoxOfficeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.boxoffice_item, viewGroup, false);
        BoxOfficeViewHolder boxOfficeViewHolder = new BoxOfficeViewHolder(view);
        return boxOfficeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoxOfficeViewHolder boxOfficeViewHolder, int i) {
        WeeklyBoxOfficeList weeklyBoxOfficeList = weeklyBoxOfficeLists.get(i);
        boxOfficeViewHolder.rankTextView.setText(weeklyBoxOfficeList.getRank() + "");
        String title = weeklyBoxOfficeList.getMovieNm();
        boxOfficeViewHolder.movieNameTextView.setText(title);
        boxOfficeViewHolder.count1TextView.setText("일일 관객수 : " + formatter.format(Integer.parseInt(weeklyBoxOfficeList.getAudiCnt()))); //일일 관객수
        boxOfficeViewHolder.count2TextView.setText("누적 관객수 : " + formatter.format(Integer.parseInt(weeklyBoxOfficeList.getAudiAcc()))); //누적관객수

        boxOfficeViewHolder.ll_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, sampleMovieActivity.class);
                intent.putExtra("title", title);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return weeklyBoxOfficeLists.size();
    }

    public class BoxOfficeViewHolder extends RecyclerView.ViewHolder {
        TextView rankTextView;
        TextView movieNameTextView;
        TextView count1TextView;
        TextView count2TextView;
        LinearLayout ll_box;


        public BoxOfficeViewHolder(@NonNull View itemView) {
            super(itemView);
            rankTextView = itemView.findViewById(R.id.rank);
            movieNameTextView = itemView.findViewById(R.id.movie_name);
            count1TextView = itemView.findViewById(R.id.count1);
            count2TextView = itemView.findViewById(R.id.count2);
            ll_box = itemView.findViewById(R.id.ll_box);
        }
    }
}
