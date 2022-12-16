package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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
import com.example.final_java_project.R;
import com.example.final_java_project.list_adapter.CustomGuideListView;
import com.example.final_java_project.setting_screen.guide_main_screen_setting_activity;
import com.example.final_java_project.setting_screen.tour_main_screen_setting_activity;

import java.io.File;
import java.util.ArrayList;

public class guide_main_screen_activity extends AppCompatActivity {
    String guideName;
    String guideRegion;
    String guideProfileMessage;
    String guideId;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_main_screen);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setCustomView(R.layout.first_screen_appbar);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        ListView listView;
        listView = findViewById(R.id.listview);

        guideId = getIntent().getStringExtra("GuideId");

        String[] title = {"김건휘", "조준영", "김형국",
                "서창희", "이상준"};
        String[] nameId = {"1", "2", "3", "4", "5"};
        ArrayList<CustomGuideListView.ListData> listViewData = new ArrayList<>();
        int i = 0;
        for (int j = 0; j < 30; j++) {
            if(i >= 5) {
                i=0;
            }
            CustomGuideListView.ListData listData = new CustomGuideListView.ListData();
            listData.title = title[i];
            listData.body_2 = "빠르게 답변해드립니다.";
            listData.nameId = nameId[i];
            listViewData.add(listData);
            i++;
        }
        ListAdapter oAdapter = new CustomGuideListView(listViewData);
        listView.setAdapter(oAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nameId = listViewData.get(position).nameId;
                Intent intentGuideChat = new Intent(getApplicationContext(),
                        guide_chat_screen.class);
                intentGuideChat.putExtra("code", nameId);
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

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.guide_profile_button:
                TextView nameTextView = findViewById(R.id.guide_name_text);
                TextView regionTextView = findViewById(R.id.guide_region_text);
                TextView myProfileMsgText = findViewById(R.id.my_profile_message_text);
                guideName = nameTextView.getText().toString();
                guideRegion = regionTextView.getText().toString();
                guideProfileMessage = myProfileMsgText.getText().toString();
                Intent intentGuideProfile =
                        new Intent(getApplicationContext(),
                                guide_main_screen_setting_activity.class);
                intentGuideProfile.putExtra("GuideId",guideId);
                intentGuideProfile.putExtra("GuideName", guideName);
                intentGuideProfile.putExtra("GuideRegion", guideRegion);
                intentGuideProfile.putExtra("GuideProfileMsg",guideProfileMessage);
                launcher.launch(intentGuideProfile);
                break;

            case R.id.queston_button:
                CustomDialog customDialog;
                customDialog = new CustomDialog(guide_main_screen_activity.this,3);
                customDialog.show();
                break;

        }

    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult data) {
                    Log.d("TAG", "data : " + data);
                    if (data.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = data.getData();
                        guideName = intent.getStringExtra("NewGuideName");
                        guideRegion = intent.getStringExtra("NewGuideRegion");
                        guideProfileMessage = intent.getStringExtra("NewGuideProfileMsg");
                        TextView nameTextView = findViewById(R.id.guide_name_text);
                        TextView regionTextView = findViewById(R.id.guide_region_text);
                        TextView myProfileMsgText = findViewById(R.id.my_profile_message_text);
                        nameTextView.setText("이름 : " + guideName);
                        regionTextView.setText("현재 거주지 : " + guideRegion);
                        myProfileMsgText.setText("상태메세지 : " + guideProfileMessage);
                        Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();
                        //textView.setText("선택한 거주지 : " + result);
                    }
                }
            });



}


