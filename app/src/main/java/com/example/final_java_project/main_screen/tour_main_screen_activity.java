package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.R;

import java.util.ArrayList;
import java.util.List;

public class tour_main_screen_activity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_main_screen);
        getSupportActionBar().setTitle("메인화면");
        TextView myProfileText, userNameText, userRegionText, userPeriodText, guideListText;
        myProfileText = (TextView) findViewById(R.id.my_profile);
        userNameText = (TextView) findViewById(R.id.user_name_text1);
        userRegionText = (TextView) findViewById(R.id.user_region_text);
        userPeriodText = (TextView) findViewById(R.id.user_period_text);
        guideListText = (TextView) findViewById(R.id.guide_list_text);
        myProfileText.setTextColor(Color.parseColor("#808080"));
        userNameText.setTextColor(Color.parseColor("#808080"));
        userRegionText.setTextColor(Color.parseColor("#808080"));
        userPeriodText.setTextColor(Color.parseColor("#808080"));
        guideListText.setTextColor(Color.parseColor("#808080"));

        ListView listView;
        listView = findViewById(R.id.listview);
        ImageView mainimage = findViewById(R.id.mainImage);


        String[] title = {"응우옌 꾸엉 휘 김(김건휘)", "응우옌 준니엔 조(조발캔)", "응우옌 꾹 형궈(김발칸)",
        "응우옌 슈 창훼이(밤톨이)","응우옌 쯔 캐박(밤톨이 견주)"};
        String[] body_1 = { " 별점 : 4.7 / 가이드 수 + 14", " 별졈:0.01 / 가이드 수 + 102",
                " 별졈:4.9 / 가이드 수 + 1024", " 별졈:4.9 / 가이드 수 + 14", " 별졈:4.7 / 가이드 수 + 14"};
        int[] id = {R.drawable.me,R.drawable.jo,R.drawable.kkuk,R.drawable.bam,R.drawable.bam };
        ArrayList<CustomListView.ListData> listViewData = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            CustomListView.ListData listData = new CustomListView.ListData();


            listData.mainImage =id[i];
            listData.title = title[i];
            listData.body_1 = body_1[i];
            listData.body_2 = "경기도 다낭시 / 평균 응답시간 5 ~ 15";
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
    }
}



