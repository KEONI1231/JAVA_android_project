package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class tour_chat_screen extends AppCompatActivity {

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_chat);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.first_screen_appbar);

        LinearLayout gravity_set = findViewById(R.id.gravity_set);
        ListView listView;
        listView = findViewById(R.id.listview);

        String[] title = {"김건휘(가이드)","나","김건휘(가이드)","나","김건휘(가이드)"};
        String me = "나";
        String[] body_1 = {"빠르고 정확하게, 친절하게 도와드립니다!!!", "현지인 이다! 나는!! 항쿡말 자알 몯해!@!!", "완벽주의자. 기적의 60키로 감량",
                "왈왈!!!왈왈와로알!!!와라라랄!!!왈!", "밤톨아 밥먹자~"};
        Boolean message_left = false;
        int[] id = {R.drawable.me, R.drawable.jo, R.drawable.kkuk, R.drawable.bam, R.drawable.bam};
        ArrayList<CustomChatView.ListData> listViewData = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            CustomChatView.ListData listData = new CustomChatView.ListData();

            if(title[i].equals(me) == true) {
                message_left = true;
                //gravity_set.setLayoutMode(Gravity.RIGHT);

            }

                int align;
                if(message_left) {

                    gravity_set.setGravity(Gravity.LEFT);

                }else{

                    gravity_set.setGravity(Gravity.RIGHT);
                }

            //chatMessageContainer.(align);
                listData.mainImage = id[0];
                listData.title = title[i];
                listData.body_1 = body_1[i];
                listViewData.add(listData);


        }
        ListAdapter oAdapter = new CustomChatView(listViewData);
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



