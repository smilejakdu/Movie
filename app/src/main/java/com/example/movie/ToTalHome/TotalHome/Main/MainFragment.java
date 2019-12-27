package com.example.movie.ToTalHome.TotalHome.Main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.movie.MovieSearchActivity;
import com.example.movie.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {
    final String BASE_URL = "http://www.kobis.or.kr";
    String API_KEY = "69054150552e63b9006e9964f1c5ac11";

    Retrofit retrofit;
    BoxOfficeService serviceBoxOffice;
    RecyclerView rcvBoxOffice;
    BoxOfficeAdapter adapterBoxOffice;

    List<WeeklyBoxOfficeList> weeklyBoxOfficeLists = new ArrayList<>();

    SimpleDateFormat mmonth = new SimpleDateFormat("YYYY-MM-dd");
    long mNow;
    Date mDate;

    TextView tv_today_day, movieSearch;
    ProgressBar progressBar;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvBoxOffice = view.findViewById(R.id.rcv_box_office);
        progressBar = view.findViewById(R.id.pb);

        movieSearch = view.findViewById(R.id.tv_movie_search);
        movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MovieSearchActivity.class);
                startActivity(intent);
            }
        });
        String strNum = initDate();
        int num = Integer.parseInt(strNum.replaceAll("-", ""));
        String parseNum = Integer.toString(num - 7);
        Log.d("qwe", "onCreate: " + parseNum);
        //Retrofit 객체생성
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        /*addConverterFactory(GsonConverterFactory.create())은
        Json을 우리가 원하는 형태로 만들어주는 Gson라이브러리와 Retrofit2에 연결하는 코드입니다 */
        progressBar.setVisibility(View.VISIBLE);
        serviceBoxOffice = retrofit.create(BoxOfficeService.class);
        serviceBoxOffice.getBoxOffice(API_KEY, parseNum).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Log.d("retro", 1 + "");
                    Result result = response.body();
                    BoxOfficeResult boxOfficeResult = result.getBoxOfficeResult();
                    List<WeeklyBoxOfficeList> weeklyBoxOfficeListLIst2 = boxOfficeResult.getWeeklyBoxOfficeList();
                    for (WeeklyBoxOfficeList weeklyBoxOffice : weeklyBoxOfficeListLIst2) {
                        weeklyBoxOfficeLists.add(weeklyBoxOffice);
                    }

                    adapterBoxOffice = new BoxOfficeAdapter(weeklyBoxOfficeLists, getContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rcvBoxOffice.setLayoutManager(linearLayoutManager);
                    rcvBoxOffice.setAdapter(adapterBoxOffice);
                    progressBar.setVisibility(View.GONE);
                } else {
                    Log.d("retro", 2 + "Error");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }


    private String initDate() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        String strNum = mmonth.format(mDate);
        return strNum;
    }
}
