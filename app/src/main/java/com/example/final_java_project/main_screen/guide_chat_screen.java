package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
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
import com.example.final_java_project.R;
import com.example.final_java_project.list_adapter.CustomGuideChatView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class guide_chat_screen extends AppCompatActivity {
    String tourId;
    String guideId;
    int fireCnt = 0;
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

        tourId = getIntent().getStringExtra("tourId");
        guideId = getIntent().getStringExtra("GuideId");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference docRef = db.collection(tourId+"!@#"+guideId).document("state");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    ListView listView;
                    listView = findViewById(R.id.listview);
                    List<String> title = new ArrayList<>();
                    List<String> body_1 = new ArrayList<>();
                    FirebaseFirestore firestore1 = FirebaseFirestore.getInstance();
                    ArrayList<CustomGuideChatView.ListData> listViewData = new ArrayList<>();
                    listViewData.clear();
                    int[] getFireCntRe = {0};
                    firestore1.collection(tourId+"!@#"+guideId).orderBy("time", Query.Direction.ASCENDING)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            title.add(document.get("id").toString());
                                            body_1.add(document.get("text").toString());
                                            CustomGuideChatView.ListData listData = new CustomGuideChatView.ListData();
                                            listData.title = title.get(getFireCntRe[0]);
                                            listData.body_1 = body_1.get(getFireCntRe[0]);
                                            listData.id = guideId;
                                            getFireCntRe[0]++;
                                            listViewData.add(listData);
                                            ListAdapter oAdapter = new CustomGuideChatView(listViewData);
                                            listView.setAdapter(oAdapter);
                                            // Log.d(TAG, document.getId() + " => " + document.getData());
                                        }
                                    } else {
                                        System.out.println("any Data");
                                    }
                                }
                            });
                    //Log.d(TAG, "Current data: " + snapshot.getData());
                } else {

                }
            }
        });

        List<String> title = new ArrayList<>();
        List<String> body_1 = new ArrayList<>();
        ArrayList<CustomGuideChatView.ListData> listViewData = new ArrayList<>();
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
                                CustomGuideChatView.ListData listData = new CustomGuideChatView.ListData();
                                listData.title = title.get(fireCnt);
                                listData.body_1 = body_1.get(fireCnt);
                                listData.id = guideId;
                                fireCnt++;
                                listViewData.add(listData);
                                ListAdapter oAdapter = new CustomGuideChatView(listViewData);
                                listView.setAdapter(oAdapter);
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
    public void onButtonClick(View view){
        String text;
        switch (view.getId()) {
            case R.id.send_message:
                EditText editText1 = findViewById(R.id.chat_editText);
                text = editText1.getText().toString();
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                LocalDate now1 = LocalDate.now();
                LocalTime now2 = LocalTime.now();
                Map<String, Object> city = new HashMap<>();
                Map<String, Object> city1= new HashMap<>();
                city1.put("state",now2);
                city.put("id", guideId  );
                city.put("text", text);
                city.put("time",now1.toString()+now2.toString() );
                firestore.collection(tourId+"!@#"+guideId).document()
                        .set(city)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "전송 완료", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Error 발생.", Toast.LENGTH_LONG).show();
                            }
                        });
                firestore.collection(tourId+"!@#"+guideId).document("state")
                        .set(city1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //finish();
                                //Text(getApplicationContext(), "전송완료", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Toast.makeText(getApplicationContext(), "아이디가 중복되었거나 다른 문제가 발생하였습니다.", Toast.LENGTH_LONG).show();
                            }
                        });

                ListView listView;
                listView = findViewById(R.id.listview);
                List<String> title = new ArrayList<>();
                List<String> body_1 = new ArrayList<>();
                FirebaseFirestore firestore1 = FirebaseFirestore.getInstance();
                ArrayList<CustomGuideChatView.ListData> listViewData = new ArrayList<>();
                listViewData.clear();
                int[] getFireCntRe = {0};
                firestore1.collection(tourId+"!@#"+guideId).orderBy("time", Query.Direction.ASCENDING)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        title.add(document.get("id").toString());
                                        body_1.add(document.get("text").toString());
                                        CustomGuideChatView.ListData listData = new CustomGuideChatView.ListData();
                                        listData.title = title.get(getFireCntRe[0]);
                                        listData.body_1 = body_1.get(getFireCntRe[0]);
                                        listData.id = guideId;
                                      //  Toast.makeText(getApplicationContext(), guideId, Toast.LENGTH_LONG).show();
                                        getFireCntRe[0]++;
                                        listViewData.add(listData);
                                        ListAdapter oAdapter = new CustomGuideChatView(listViewData);
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



