package com.example.movie.ToTalHome.TotalHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import com.example.movie.R;
import com.example.movie.ToTalHome.TotalHome.Main.MainFragment;
import com.example.movie.ToTalHome.TotalHome.PhoneBook.PhoneBookFragment;
import com.example.movie.ToTalHome.TotalHome.map.MapFragment;
import com.example.movie.util.ActivityUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bnv = findViewById(R.id.bnv);
        BottomNavigationInit(bnv);
    }

    private void BottomNavigationInit(BottomNavigationView bnv) {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), MainFragment.newInstance(), R.id.fl_main);
        bnv.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.m_movie: {
                    changeScreen(itemId, MainFragment.newInstance());
                    return true;
                }
                case R.id.m_map: {
                    changeScreen(itemId, MapFragment.newInstance());
                    return true;
                }
                case R.id.m_call: {
                    changeScreen(itemId, PhoneBookFragment.newInstance());
                    return true;
                }
                default: {
                    return false;
                }
            }
        });
    }

    public void changeScreen(int itemId, Fragment fragment) {
        if (itemId != bnv.getSelectedItemId()) { //같은 탭을 누르지 않았을 경우만 이동.
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fl_main);
        }
    }

    @Override
    public void onBackPressed() {
//       super.onBackPressed(); 를 주석처리하여 뒤로 가기 키를 눌러도 액티비티가 종료되지 않게 하였습니다.
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle("종료 확인");
        builder.setMessage("정말 갈꺼야?! ㅜㅜㅜ");
        builder.setPositiveButton("확인", (dialog, which) ->
                finish());
        builder.setNegativeButton("취소", null);
        builder.show();
    }
}
