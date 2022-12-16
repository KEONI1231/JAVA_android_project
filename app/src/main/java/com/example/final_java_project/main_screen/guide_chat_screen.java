package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

        String[] title = {"김건휘(가이드)", "나", "김건휘(가이드)", "나", "김건휘(가이드)","김건휘(가이드)","나","김건휘(가이드)", "김건휘(가이드)"};

        String[] body_1 = {"안녕하세요 건휘님ㅎㅎ", "네 안녕하세요 가이드님", "어떤걸 도와드릴까요?",
                "미케해변 주변에 자리를 잡았는데\n이 주변에 현지인들 많이 가는술집이 있나요?", "아네 그 주변에 좋은 술집 많아요!!", "일단 그 주변에 칵테일 바가 있는데\n" +
                "여행객들도 많이 없고 거의 현지인들만 가요", "분위기가 많이 시끌시끌 한가요?","아니요 분위기는 많이 안시끄러워요ㅎㅎ", "치안도 좋아서 안심하셔도 됩니다"};
        ArrayList<CustomGuideChatView.ListData> listViewData = new ArrayList<>();
        int i = 0;
        for (int j = 0; j <  30; ++j) {
            CustomGuideChatView.ListData listData = new CustomGuideChatView.ListData();
            if(i>=9) {
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
    public void onButtonClick(View view){
        String text;
        switch (view.getId()) {

            case R.id.send_message:
                EditText editText = findViewById(R.id.chat_editText);
                text = editText.getText().toString();

                break;

        }
    }
}



