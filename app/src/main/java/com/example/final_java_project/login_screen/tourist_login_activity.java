package com.example.final_java_project.login_screen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.R;
import com.example.final_java_project.main_screen.tour_main_screen_activity;

public class tourist_login_activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tourist_login);
        //앱바 텍스트
        getSupportActionBar().setTitle("여행객 로그인");
        // id, pw 텍스트 색조정
      
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.login_appbar);

    }
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.login_try_btn:
                Intent intentGuideLogin = new Intent(getApplicationContext(),
                        tour_main_screen_activity.class);
                startActivity(intentGuideLogin);
                break;

        }
    }
}
