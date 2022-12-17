package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class tour_main_screen_activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    String myName;
    String defaultTripRegion;
    String myTripRegion;
    String myTripPeriod;
    String tourId;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

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

        tourId = getIntent().getStringExtra("TourId");
        defaultTripRegion = getIntent().getStringExtra("TripRegion");

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference docRef = firestore.collection("tour_user").document(tourId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        TextView nameView = findViewById(R.id.user_name_text);
                        TextView tripRegionView = findViewById(R.id.user_region_text);
                        nameView.setText("이름 : " + document.get("name").toString());
                        tripRegionView.setText("현재 여행지 : " + document.get("trip_region").toString());
                    } else {
                        //Toast.makeText(getApplicationContext(), "id또는 pw가 일치하지 않습니다..", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "에러 발생. 네트워크 체크 요망", Toast.LENGTH_LONG).show();
                }
            }
        });


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
                        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        List<ActivityManager.RunningTaskInfo> info;
                        info = activityManager.getRunningTasks(1);

                        if(info.get(0).topActivity.getClassName().equals(tour_main_screen_activity.this.getClass().getName())) {

                        }
                        else {
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
                        customDialog = new CustomDialog(tour_main_screen_activity.this, 1);
                        customDialog.show();
                        break;

                }
                return true;
            }
        });
        List<String> title = new ArrayList<>();
        List<String> body_1 = new ArrayList<>();
        List<String> body_2 = new ArrayList<>();
        List<String> body_3 = new ArrayList<>();
        List<String> guideIdChat = new ArrayList<>();
        int[] fireCnt = {0};
        ArrayList<CustomListView.ListData> listViewData = new ArrayList<>();
        FirebaseFirestore firestore1 = FirebaseFirestore.getInstance();
        firestore1.collection("guide_user")
                .whereEqualTo("guide_region", defaultTripRegion)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //  System.out.println(document.get("guide_region").toString());
                                //System.out.println(document.getData());

                                title.add("이름 : " + document.get("name") + "              " +
                                        "                                                  " +
                                        "                                                  " +
                                        "                                                  " +
                                        "                                                  " +
                                        "                                                  " +
                                        "                                                  " +
                                        "                                                  " +
                                        "                                                  " +
                                        "" );
                                body_1.add((String) ("별점 : " + document.get("guide_aver_star") + " / "
                                        + "가이드 수 : " + document.get("guide_total_count")));
                                body_2.add((String) document.get("guide_region"));
                                body_3.add((String) document.get("guide_profile_message"));
                                guideIdChat.add((String) document.get("id"));
                                //System.out.println(body_1.get(fireCnt));
                                int[] id = {R.drawable.character_icon};

                                CustomListView.ListData listData = new CustomListView.ListData();
                                listData.mainImage = id[0];
                                listData.title = title.get(fireCnt[0]);
                                listData.body_1 = body_1.get(fireCnt[0]);
                                listData.body_2 = body_2.get(fireCnt[0]);
                                listData.body_3 = body_3.get(fireCnt[0]);
                                listData.id = guideIdChat.get(fireCnt[0]);
                                listViewData.add(listData);
                                ListAdapter oAdapter = new CustomListView(listViewData);
                                listView.setAdapter(oAdapter);

                                fireCnt[0]++;
                            }


                        } else {
                            listViewData.clear();
                            System.out.println("error");
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }

                });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String guideIdChatIntent = listViewData.get(position).id;
                Intent intentGuideLogin =
                        new Intent(getApplicationContext(),
                                tour_chat_screen.class);
                intentGuideLogin.putExtra("TourId", tourId);
                intentGuideLogin.putExtra("guideId", guideIdChatIntent);

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
                //intentMyProfile.putExtra("UserId", myName);
                intentMyProfile.putExtra("TourId", tourId);
                intentMyProfile.putExtra("Name", myName);
                intentMyProfile.putExtra("Region", myTripRegion);
                intentMyProfile.putExtra("Period", myTripPeriod);
                launcher.launch(intentMyProfile);
                //finish();
                //startActivity(new Intent(tour_main_screen_activity.this, tour_main_screen_activity.class));

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
                        myName = intent.getStringExtra("NewName");
                        myTripRegion = intent.getStringExtra("NewRegion");
                        myTripPeriod = intent.getStringExtra("NewPeriod");
                        defaultTripRegion = myTripRegion;
                        TextView nameTextView = findViewById(R.id.user_name_text);
                        TextView regionTextView = findViewById(R.id.user_region_text);

                        nameTextView.setText("이름 : " + myName);
                        regionTextView.setText("현재 여행지 : " + myTripRegion);
                        Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();

                        int[] fireCntRe = {0};
                        ListView listView;
                        listView = findViewById(R.id.listview);
                        List<String> title = new ArrayList<>();
                        List<String> body_1 = new ArrayList<>();
                        List<String> body_2 = new ArrayList<>();
                        List<String> body_3 = new ArrayList<>();
                        List<String> guideIdChat1 = new ArrayList<>();
                        ArrayList<CustomListView.ListData> listViewData = new ArrayList<>();

                        FirebaseFirestore firestore1 = FirebaseFirestore.getInstance();
                        listViewData.clear();
                        fireCntRe[0] = 0;
                        firestore1.collection("guide_user")
                                .whereEqualTo("guide_region", defaultTripRegion)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                //  System.out.println(document.get("guide_region").toString());
                                                //System.out.println(document.getData());

                                                    title.add(document.get("name").toString());
                                                    body_1.add("별점 : " + document.get("guide_aver_star").toString() + " / "
                                                            + "가이드 수 : " + document.get("guide_total_count").toString());
                                                    body_2.add(document.get("guide_region").toString());
                                                    body_3.add(document.get("guide_profile_message").toString());
                                                    guideIdChat1.add((String) document.get("id"));
                                                    System.out.println(body_1.get(fireCntRe[0]));
                                                    int[] id = {R.drawable.character_icon};
                                                    CustomListView.ListData listData = new CustomListView.ListData();
                                                    listData.mainImage = id[0];
                                                    listData.title = title.get(fireCntRe[0]);
                                                    listData.body_1 = body_1.get(fireCntRe[0]);
                                                    listData.body_2 = body_2.get(fireCntRe[0]);
                                                    listData.body_3 = body_3.get(fireCntRe[0]);
                                                    listData.id = guideIdChat1.get(fireCntRe[0]);
                                                    listViewData.add(listData);
                                                    fireCntRe[0]++;
                                                    ListAdapter oAdapter = new CustomListView(listViewData);
                                                    listView.setAdapter(oAdapter);
                                            }
                                            if(fireCntRe[0] == 0) {
                                                listViewData.clear();
                                                ListAdapter oAdapter = new CustomListView(listViewData);
                                                listView.setAdapter(oAdapter);
                                            }
                                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                    String guideIdChatIntent = listViewData.get(position).id;
                                                    Intent intentGuideLogin =
                                                            new Intent(getApplicationContext(),
                                                                    tour_chat_screen.class);
                                                    intentGuideLogin.putExtra("TourId", tourId);
                                                    intentGuideLogin.putExtra("guideId", guideIdChatIntent);
                                                    startActivity(intentGuideLogin);
                                                }
                                            });


                                        }
                                    }

                                });

                    }
                }
            });
}



