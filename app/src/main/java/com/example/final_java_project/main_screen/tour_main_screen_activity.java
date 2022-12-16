package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.CustomDialog;
import com.example.final_java_project.MainActivity;
import com.example.final_java_project.R;
import com.example.final_java_project.list_adapter.CustomListView;
import com.example.final_java_project.login_screen.guide_login_activity;
import com.example.final_java_project.login_screen.signup_acivity;
import com.example.final_java_project.login_screen.tourist_login_activity;
import com.example.final_java_project.setting_screen.tour_main_screen_setting_activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class tour_main_screen_activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    String myName;
    String myTripRegion;
    String myTripPeriod;
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


        String tourId = getIntent().getStringExtra("TourId");
        System.out.println(tourId);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);
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
                        intentTourChat.putExtra("TourId",tourId);
                        startActivity(intentTourChat);
                        break;
                    case R.id.queston_tab:

                        CustomDialog customDialog;
                        customDialog = new CustomDialog(tour_main_screen_activity.this,1);
                        customDialog.show();
                        break;

                }
                return true;
            }
        });
        String[] title = {"김건휘 가이드","서창희 가이드","조준영 가이드","김형국 가이드","김재원 가이드",};
        String[] body_1 = {" 별점 : 4.7 / 가이드 수 + 14", " 별점:0.01 / 가이드 수 + 102",
                " 별점:4.9 / 가이드 수 + 1024", " 별점:4.9 / 가이드 수 + 14", " 별점:4.7 / 가이드 수 + 14"};
        String[] body_3 = {"빠르고 정확하게, 친절하게 도와드립니다!!!", "다낭에서 5년 정도 살았습니다. 친절합니다!", "한번가는 여행 확실하게 알려드립니다!",
                "평균 3분안에 답장해드립니다.", "현지에서 9년 정도 살았습니다!!"};
        int[] id = {R.drawable.character_icon};
        ArrayList<CustomListView.ListData> listViewData = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            CustomListView.ListData listData = new CustomListView.ListData();
            listData.mainImage = id[0];
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
                Intent intentGuideLogin =
                        new Intent(getApplicationContext(),
                                tour_chat_screen.class);
                startActivity(intentGuideLogin);

            }
        });
        listView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                listView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.my_profile_setting:
                TextView nameTextView = findViewById(R.id.user_name_text);
                TextView regionTextView = findViewById(R.id.user_region_text);

                myName = nameTextView.getText().toString();
                myTripRegion = regionTextView.getText().toString();


                Intent intentMyProfile =
                        new Intent(getApplicationContext(),
                                tour_main_screen_setting_activity.class);
                intentMyProfile.putExtra("Name", myName);
                intentMyProfile.putExtra("Region", myTripRegion);
                intentMyProfile.putExtra("Period", myTripPeriod);
                launcher.launch(intentMyProfile);
                break;
        }
    }
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult data)
                {

                    Log.d("TAG", "data : " + data);
                    if (data.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent intent = data.getData();
                        myName = intent.getStringExtra ("NewName");
                        myTripRegion = intent.getStringExtra ("NewRegion");
                        myTripPeriod = intent.getStringExtra ("NewPeriod");

                        TextView nameTextView = findViewById(R.id.user_name_text);
                        TextView regionTextView = findViewById(R.id.user_region_text);

                        nameTextView.setText("이름 : " +myName);
                        regionTextView.setText("현재 여행지 : "+myTripRegion);
                        Toast.makeText(getApplicationContext(), "저장되었습니다.",Toast.LENGTH_LONG).show();
                        //textView.setText("선택한 거주지 : " + result);
                    }
                }
            });
}



