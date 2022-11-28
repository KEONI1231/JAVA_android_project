package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.final_java_project.R;
import com.example.final_java_project.login_screen.guide_login_activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class tour_main_screen_activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_main_screen);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.first_screen_appbar);
        ListView listView;
        listView = findViewById(R.id.listview);

        bottomNavigationView = findViewById(R.id.navigationView1);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //item을 클릭시 id값을 가져와 FrameLayout에 fragment.xml띄우기
                    case R.id.home_tab:
                        Intent intentGuideLogin =
                                new Intent(getApplicationContext(),
                                        tour_main_screen_activity.class);
                        startActivity(intentGuideLogin);
                        break;
                    case R.id.chat_tab:
                        Intent intentTourChat =
                                new Intent(getApplicationContext(),
                                        tour_chat_screen.class);
                        startActivity(intentTourChat);
                        break;
                    case R.id.setting_tab:
                        break;


                }
                return true;
            }
        });

        String[] title = {"응우옌 꾸엉 휘 김(김건휘)", "응우옌 준니엔 조(조발캔)", "응우옌 꾹 형궈(김발칸)",
                "응우옌 슈 창훼이(밤톨이)", "응우옌 쯔 캐박(밤톨이 견주)"};
        String[] body_1 = {" 별점 : 4.7 / 가이드 수 + 14", " 별점:0.01 / 가이드 수 + 102",
                " 별점:4.9 / 가이드 수 + 1024", " 별점:4.9 / 가이드 수 + 14", " 별점:4.7 / 가이드 수 + 14"};
        String[] body_3 = {"빠르고 정확하게, 친절하게 도와드립니다!!!", "현지인 이다! 나는!! 항쿡말 자알 몯해!@!!", "완벽주의자. 기적의 60키로 감량",
                "왈왈!!!왈왈와로알!!!와라라랄!!!왈!", "밤톨아 밥먹자~"};
        int[] id = {R.drawable.me, R.drawable.jo, R.drawable.kkuk, R.drawable.bam, R.drawable.bam};
        ArrayList<CustomListView.ListData> listViewData = new ArrayList<>();



        for (int i = 0; i < 5; ++i) {
            CustomListView.ListData listData = new CustomListView.ListData();
            listData.mainImage = id[i];
            listData.title = title[i];
            listData.body_1 = body_1[i];
            listData.body_2 = "경기도 다낭시 / 평균 응답시간 5 ~ 15";
            listData.body_3 = body_3[i];
            listViewData.add(listData);
        }


        ListAdapter oAdapter = new CustomListView(listViewData);
        listView.setAdapter(oAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickName = listViewData.get(position).title;

            }
        });
        listView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                listView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

    }

}



