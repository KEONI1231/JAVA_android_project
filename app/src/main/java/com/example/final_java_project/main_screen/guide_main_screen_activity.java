package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import androidx.appcompat.app.AppCompatActivity;
import com.example.final_java_project.CustomDialog;
import com.example.final_java_project.R;
import com.example.final_java_project.list_adapter.CustomGuideListView;
import com.example.final_java_project.setting_screen.guide_main_screen_setting_activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class guide_main_screen_activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    String guideName;
    String guideRegion;
    String guideProfileMessage;
    String guideId;
    String defaultRegion;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
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
        defaultRegion = getIntent().getStringExtra("GuideRegion");


        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference docRef = firestore.collection("guide_user").document(guideId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        TextView nameView = findViewById(R.id.guide_name_text);
                        TextView guideRegionView = findViewById(R.id.guide_region_text);
                        TextView guideAverView = findViewById(R.id.guide_aver_score);
                        TextView guideTotalCountView = findViewById(R.id.guide_total_counting);
                        TextView guideProfileView = findViewById(R.id.my_profile_message_text);
                        defaultRegion = document.get("guide_region").toString();
                        nameView.setText("이름 : " + document.get("name").toString());
                        guideRegionView.setText("현재 여행지 : " + document.get("guide_region").toString());
                        guideAverView.setText("내 평점 : " + document.get("guide_aver_star").toString());
                        guideTotalCountView.setText("총 가이드 횟수 : " + document.get("guide_total_count").toString());
                        guideProfileView.setText("상태메세지 : " + document.get("guide_profile_message").toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "Error 발생", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error 발생", Toast.LENGTH_LONG).show();
                }
            }
        });

        List<String> title = new ArrayList<>();
        List<String> body_1 = new ArrayList<>();
        List<String> nameId = new ArrayList<>();
        int[] fireCnt = {0};
        ArrayList<CustomGuideListView.ListData> listViewData = new ArrayList<>();

        FirebaseFirestore firestore1 = FirebaseFirestore.getInstance();
        firestore1.collection("tour_user")
                .whereEqualTo("trip_region", defaultRegion)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                title.add("고객 이름 : " + document.get("name").toString());
                                body_1.add("별점 : " + document.get("trip_region").toString());
                                nameId.add(document.get("id").toString());
                                CustomGuideListView.ListData listData = new CustomGuideListView.ListData();
                                listData.title = title.get(fireCnt[0]);
                                listData.body_2 = body_1.get(fireCnt[0]);
                                listData.Id = nameId.get(fireCnt[0]);
                                listViewData.add(listData);
                                ListAdapter oAdapter = new CustomGuideListView(listViewData);
                                listView.setAdapter(oAdapter);
                                fireCnt[0]++;
                            }
                        } else {
                            listViewData.clear();
                            System.out.println("error");
                        }
                    }

                });

        bottomNavigationView = findViewById(R.id.navigationView1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home_tab:
                        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        List<ActivityManager.RunningTaskInfo> info;
                        info = activityManager.getRunningTasks(1);
                        if (info.get(0).topActivity.getClassName().equals(guide_main_screen_activity.this.getClass().getName())) {

                        } else {
                            Intent intentGuideLogin =
                                    new Intent(getApplicationContext(),
                                            tour_main_screen_activity.class);
                            startActivity(intentGuideLogin);
                        }
                        break;
                    case R.id.chat_tab:
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KEONI1231/JAVA_android_project"));
                        startActivity(myIntent);
                        break;
                    case R.id.exit_tab:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        break;
                    case R.id.queston_tab:
                        CustomDialog customDialog;
                        customDialog = new CustomDialog(guide_main_screen_activity.this, 3);
                        customDialog.show();
                        break;
                }
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nameId = listViewData.get(position).Id;
                Intent intentGuideChat = new Intent(getApplicationContext(),
                        guide_chat_screen.class);
                intentGuideChat.putExtra("tourId", nameId);
                intentGuideChat.putExtra("GuideId", guideId);
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
                intentGuideProfile.putExtra("GuideId", guideId);
                intentGuideProfile.putExtra("GuideName", guideName);
                intentGuideProfile.putExtra("GuideRegion", guideRegion);
                intentGuideProfile.putExtra("GuideProfileMsg", guideProfileMessage);
                launcher.launch(intentGuideProfile);
                break;

            case R.id.queston_button:
                CustomDialog customDialog;
                customDialog = new CustomDialog(guide_main_screen_activity.this, 3);
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
                        defaultRegion = guideRegion;
                        TextView nameTextView = findViewById(R.id.guide_name_text);
                        TextView regionTextView = findViewById(R.id.guide_region_text);
                        TextView myProfileMsgText = findViewById(R.id.my_profile_message_text);
                        nameTextView.setText("이름 : " + guideName);
                        regionTextView.setText("현재 거주지 : " + guideRegion);
                        myProfileMsgText.setText("상태메세지 : " + guideProfileMessage);
                        Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();
                        ArrayList<CustomGuideListView.ListData> listViewData = new ArrayList<>();
                        ListView listView;
                        listView = findViewById(R.id.listview);
                        List<String> title = new ArrayList<>();
                        List<String> body_1 = new ArrayList<>();
                        List<String> nameId = new ArrayList<>();
                        int[] fireCntRe = {0};

                        FirebaseFirestore firestore1 = FirebaseFirestore.getInstance();
                        firestore1.collection("tour_user")
                                .whereEqualTo("trip_region", defaultRegion)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                title.add("고객 이름 : " + document.get("name").toString());
                                                body_1.add("별점 : " + document.get("trip_region").toString());
                                                nameId.add(document.get("id").toString());
                                                CustomGuideListView.ListData listData = new CustomGuideListView.ListData();
                                                listData.title = title.get(fireCntRe[0]);
                                                listData.body_2 = body_1.get(fireCntRe[0]);
                                                listData.Id = nameId.get(fireCntRe[0]);
                                                listViewData.add(listData);
                                                ListAdapter oAdapter = new CustomGuideListView(listViewData);
                                                listView.setAdapter(oAdapter);
                                                fireCntRe[0]++;
                                            }
                                            if (fireCntRe[0] == 0) {
                                                listViewData.clear();
                                                ListAdapter oAdapter = new CustomGuideListView(listViewData);
                                                listView.setAdapter(oAdapter);
                                            }
                                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                    String nameId = listViewData.get(position).Id;
                                                    Intent intentGuideChat = new Intent(getApplicationContext(),
                                                            guide_chat_screen.class);
                                                    intentGuideChat.putExtra("TourId", nameId);
                                                    intentGuideChat.putExtra("GuideId", guideId);
                                                    startActivity(intentGuideChat);
                                                }
                                            });
                                        }
                                    }
                                });
                    }
                }
            });
}


