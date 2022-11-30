package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.R;
import com.example.final_java_project.list_adapter.CustomGuideListView;

import java.util.ArrayList;

public class guide_main_screen_activity extends AppCompatActivity {
    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_main_screen);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.first_screen_appbar);
        ListView listView;
        listView = findViewById(R.id.listview);

        String[] title = {"응우옌 꾸엉 휘 김(김건휘)", "응우옌 준니엔 조(조발캔)", "응우옌 꾹 형궈(김발칸)",
                "응우옌 슈 창훼이(밤톨이)", "응우옌 쯔 캐박(밤톨이 견주)"};
        String[] nameId = {"1","2","3","4","5"};
        int[] id = {R.drawable.me, R.drawable.jo, R.drawable.kkuk, R.drawable.bam, R.drawable.bam};
        ArrayList<CustomGuideListView.ListData> listViewData = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            CustomGuideListView.ListData listData = new CustomGuideListView.ListData();
            listData.title = title[i];
            listData.body_2 = "경기도 다낭시 / 평균 응답시간 5 ~ 15";
            listData.nameId = nameId[i];
            listViewData.add(listData);
        }
        ListAdapter oAdapter = new CustomGuideListView(listViewData);
        listView.setAdapter(oAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nameId = listViewData.get(position).nameId;
                Intent intentGuideChat = new Intent(getApplicationContext(),
                        tour_chat_screen.class);
                intentGuideChat.putExtra("code",nameId);
                startActivity(intentGuideChat);
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



