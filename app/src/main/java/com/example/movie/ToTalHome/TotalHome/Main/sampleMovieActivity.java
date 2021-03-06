package com.example.movie.ToTalHome.TotalHome.Main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.example.movie.R;
import com.example.movie.databinding.ActivitySampleMovieActivityBinding;

public class sampleMovieActivity extends AppCompatActivity {
    private ActivitySampleMovieActivityBinding binding;
    WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_movie_activity);


        setSupportActionBar(binding.tbBack);
        setTitle("Movie");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        Log.d("qwe", "sampleMovieActivity: " + title);
        //       웹 셋팅 하는 부분
        binding.wvShow.loadUrl("https://www.youtube.com/results?search_query=영화" + title);
        webSettings = binding.wvShow.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.wvShow.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
