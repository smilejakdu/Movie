package com.example.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//http://www.kobis.or.kr/kobisopenapi/homepg/main/main.do
public class MainActivity extends AppCompatActivity {
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

    TextView tv_today_day;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_today_day = findViewById(R.id.tv_today_day);
        rcvBoxOffice = findViewById(R.id.rcv_box_office);
        progressBar = findViewById(R.id.pb);
        String strNum = initDate();
        tv_today_day.setText(strNum);
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

                    adapterBoxOffice = new BoxOfficeAdapter(weeklyBoxOfficeLists, MainActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
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

    @Override
    public void onBackPressed() {
//       super.onBackPressed(); 를 주석처리하여 뒤로 가기 키를 눌러도 액티비티가 종료되지 않게 하였습니다.
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle("종료 확인");
        builder.setMessage("정말로 종료하시겠습니까 ?");
        builder.setPositiveButton("확인", (dialog, which) ->
                finish());
        builder.setNegativeButton("취소", null);
        builder.show();
    }
}