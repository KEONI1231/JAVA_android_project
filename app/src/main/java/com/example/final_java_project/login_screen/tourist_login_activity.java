package com.example.final_java_project.login_screen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
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
        TextView text1,text2;
        text1 = (TextView) findViewById(R.id.id_text);
        text2 = (TextView) findViewById(R.id.pw_text);
        text1.setTextColor(Color.parseColor("#808080"));
        text2.setTextColor(Color.parseColor("#808080"));

    }
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.login_try_btn:
                Intent intentGuideLogin =
                        new Intent(getApplicationContext(),
                                tour_main_screen_activity.class);
                startActivity(intentGuideLogin);
                break;

        }
    }
}
