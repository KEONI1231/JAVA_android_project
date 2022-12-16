package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.CustomDialogStart;
import com.example.final_java_project.R;
import com.example.final_java_project.list_adapter.CustomTourChatView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tour_chat_screen extends AppCompatActivity {
    Handler mHandler;
    Socket socket;
    PrintWriter sendWriter;
    String ip = "211.62.179.135";
    int port = 8080;
    String tourId;
    String guideId;
    int fireCnt = 0;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_chat);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.first_screen_appbar);
        ListView listView;
        listView = findViewById(R.id.listview);
        mHandler = new Handler();
        tourId = getIntent().getStringExtra("TourId");
        guideId = getIntent().getStringExtra("guideId");
        System.out.println(tourId+guideId);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        /*
        String[] title = {"김건휘(가이드)", "나", "김건휘(가이드)", "나", "김건휘(가이드)","김건휘(가이드)","나","김건휘(가이드)", "김건휘(가이드)"};

        String[] body_1 = {"안녕하세요 건휘님ㅎㅎ", "네 안녕하세요 가이드님", "어떤걸 도와드릴까요?",
                "미케해변 주변에 자리를 잡았는데\n이 주변에 현지인들 많이 가는술집이 있나요?", "아네 그 주변에 좋은 술집 많아요!!", "일단 그 주변에 칵테일 바가 있는데\n" +
                "여행객들도 많이 없고 거의 현지인들만 가요", "분위기가 많이 시끌시끌 한가요?","아니요 분위기는 많이 안시끄러워요ㅎㅎ", "치안도 좋아서 안심하셔도 됩니다"};

         */
        List<String> title = new ArrayList<>();
        List<String> body_1 = new ArrayList<>();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.tour_chat_custom_listview, null);
        ArrayList<CustomTourChatView.ListData> listViewData = new ArrayList<>();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection(tourId+"!@#"+guideId).orderBy("time", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                title.add(document.get("id").toString());
                                body_1.add(document.get("text").toString());

                                CustomTourChatView.ListData listData = new CustomTourChatView.ListData();
                                listData.title = title.get(fireCnt);
                                listData.body_1 = body_1.get(fireCnt);
                                listData.id = tourId;
                                fireCnt++;
                                listViewData.add(listData);
                                ListAdapter oAdapter = new CustomTourChatView(listViewData);
                                listView.setAdapter(oAdapter);
                               // Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                           System.out.println("any Data");
                        }
                    }
                });

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

    public void onButtonClick(View view) throws JSONException {
        String text;
        switch (view.getId()) {
            case R.id.send_message:
                EditText editText = findViewById(R.id.chat_editText);
                text = editText.getText().toString();
                if (text.equals("//chat-server-clear")) {
                    CustomDialogStart customDialog;
                    customDialog = new CustomDialogStart(tour_chat_screen.this, 0);
                    String star = "";
                    customDialog.show();
              //      System.out.println(text);
                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    DocumentReference washingtonRef = firestore.collection("tour_user").document(tourId);
                    washingtonRef
                            .update("chat_state", false)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //Log.d(TAG, "DocumentSnapshot successfully updated!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //Log.w(TAG, "Error updating document", e);
                                }
                            });
                }
                else {
                    EditText editText1 = findViewById(R.id.chat_editText);
                    text = editText1.getText().toString();
                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    LocalDate now1 = LocalDate.now();
                    LocalTime now2 = LocalTime.now();
                    Map<String, Object> city = new HashMap<>();
                    city.put("id", tourId);
                    city.put("text", text);
                    city.put("time",now1.toString()+now2.toString() );
                    firestore.collection(tourId+"!@#"+guideId).document()
                            .set(city)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //finish();
                                    Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "아이디가 중복되었거나 다른 문제가 발생하였습니다.", Toast.LENGTH_LONG).show();
                                }
                            });
                }

                ListView listView;
                listView = findViewById(R.id.listview);

                List<String> title = new ArrayList<>();
                List<String> body_1 = new ArrayList<>();
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                ArrayList<CustomTourChatView.ListData> listViewData = new ArrayList<>();
                listViewData.clear();
                int[] getFireCntRe = {0};
                firestore.collection(tourId+"!@#"+guideId).orderBy("time", Query.Direction.ASCENDING)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        title.add(document.get("id").toString());
                                        body_1.add(document.get("text").toString());
                                        CustomTourChatView.ListData listData = new CustomTourChatView.ListData();
                                        listData.title = title.get(getFireCntRe[0]);
                                        listData.body_1 = body_1.get(getFireCntRe[0]);
                                        listData.id = tourId;
                                        getFireCntRe[0]++;
                                        listViewData.add(listData);
                                        ListAdapter oAdapter = new CustomTourChatView(listViewData);
                                        listView.setAdapter(oAdapter);
                                        // Log.d(TAG, document.getId() + " => " + document.getData());
                                    }
                                } else {
                                    System.out.println("any Data");
                                }
                            }
                        });




                break;
        }
    }

}



