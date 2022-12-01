package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.R;
import com.example.final_java_project.list_adapter.CustomGuideChatView;


import java.util.ArrayList;

public class guide_chat_screen extends AppCompatActivity {
    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_chat);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.first_screen_appbar);
        LinearLayout gravity_set = findViewById(R.id.gravity_set);
        ListView listView;
        listView = findViewById(R.id.listview);
        String[] title = {"응우옌", "나", "응우옌", "나", "응우옌"};
        String[] body_1 = {"빠르고 정확하게, 친절하게 도와드립니다!!!", "현지인 이다! 나는!! 항쿡말 자알 몯해!@!!", "완벽주의자. 기적의 60키로 감량",
                "왈왈!!!왈왈와로알!!!와라라랄!!!왈!", "밤톨아 밥먹자~"};
        ArrayList<CustomGuideChatView.ListData> listViewData = new ArrayList<>();
        int i = 0;
        for (int j = 0; j <  30; ++j) {
            CustomGuideChatView.ListData listData = new CustomGuideChatView.ListData();
            if(i>=5) {
                i = 0;
            }
            listData.title = title[i];
            listData.body_1 = body_1[i];
            listViewData.add(listData);
            i = i + 1;
        }

        Intent getIntent = getIntent();
        String value = getIntent.getStringExtra("code");
        System.out.println(value);

        ListAdapter oAdapter = new CustomGuideChatView(listViewData);
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



