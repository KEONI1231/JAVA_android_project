package com.example.final_java_project.login_screen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        EditText idEditText = findViewById(R.id.id_editText);
        EditText pwEditText = findViewById(R.id.pw_editText);
        switch (view.getId()) {
            case R.id.login_try_btn:
                if(idEditText.getText().toString().length() == 0 || pwEditText.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "id, pw 모두 입력해주세요.",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "로그인 성공",Toast.LENGTH_LONG).show();
                    Intent intentTourLogin = new Intent(getApplicationContext(),
                            tour_main_screen_activity.class);
                    intentTourLogin.putExtra("TourId",idEditText.getText().toString());
                    startActivity(intentTourLogin);
                }
                break;

        }
    }
}
