package com.example.movie.ToTalHome.TotalHome.Main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;

import com.example.movie.R;
import com.example.movie.databinding.ActivitySearchMoveBinding;

public class SearchMoveActivity extends AppCompatActivity {
    private ActivitySearchMoveBinding binding;
    WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_move);

        setSupportActionBar(binding.tbBack);
        setTitle("Movie");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
