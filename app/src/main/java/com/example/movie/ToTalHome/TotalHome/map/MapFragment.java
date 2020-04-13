package com.example.movie.ToTalHome.TotalHome.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.movie.R;
import com.example.movie.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {
    private WebView webView;
    WebSettings webSettings;

    private FragmentMapBinding binding;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.wv_map);

        webView.loadUrl("https://www.google.co.kr/maps/place/CGV+%EC%9A%B8%EC%82%B0%EC%82%BC%EC%82%B0/@35.5416653,129.3367142,17z/data=!3m1!4b1!4m8!1m2!2m1!1z7Jq47IKwIOyYge2ZlOq0gA!3m4!1s0x3567cd3fa0b70997:0xbd3cc92188cd0da!8m2!3d35.5416653!4d129.3389029?hl=ko");
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

    }
}
