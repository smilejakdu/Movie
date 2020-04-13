package com.example.movie.ToTalHome.TotalHome.Main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.MovieSearchActivity;
import com.example.movie.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    TextView movieSearch;
    ProgressBar progressBar;
    private final String WATING_GREETINGS = "please wating ~ ^ ^ ";
    private ArrayList<NowMovieItem> list = new ArrayList();

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

        recyclerView = view.findViewById(R.id.rcv_now_movie);
        movieSearch = view.findViewById(R.id.tv_movie_search);
        movieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MovieSearchActivity.class);
                startActivity(intent);
            }
        });
        new Description().execute();
    }

    private class Description extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //진행다일로그 시작
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(WATING_GREETINGS);
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("https://movie.naver.com/movie/running/current.nhn").get();
                Elements mElementDataSize = doc.select("ul[class=lst_detail_t1]").select("li");
                for (Element elem : mElementDataSize) {
                    String myTitle = elem.select("dt[class=tit] a").text();
                    String myImgUrl = elem.select("div[class=thumb] a img").attr("src");
                    String director = elem.select("dt[class=tit_t2]").next().first().select("a").text();
                    list.add(new NowMovieItem(myImgUrl, myTitle, director));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            NowMovieAdapter nowMovieAdapter = new NowMovieAdapter(list, getContext());
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(nowMovieAdapter);
            progressDialog.dismiss();
        }
    }
}
