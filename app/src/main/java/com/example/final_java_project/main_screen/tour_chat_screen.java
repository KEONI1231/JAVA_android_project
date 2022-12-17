package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import org.json.JSONException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class tour_chat_screen extends AppCompatActivity {
    Handler mHandler;
    String tourId;
    String guideId;
    int fireCnt = 0;
    double starAverage = 0.0;
    double starTotalStar = 0;
    double guideTotalCount = 0;

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
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference docRef = db.collection(tourId+"!@#"+guideId).document("state");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    //Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
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
                } else {

                }
            }
        });

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
                if (text.equals("//평가하기")) {
                    CustomDialogStart customDialog;
                    customDialog = new CustomDialogStart(tour_chat_screen.this, 0);
                    customDialog.show();

                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    DocumentReference docRef = firestore.collection("guide_user").document(guideId);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                   starAverage =  Double.parseDouble(String.valueOf(document.get("guide_aver_star")));
                                   starTotalStar =  Double.parseDouble(String.valueOf(document.get("guide_total_star")));
                                   guideTotalCount = Double.parseDouble(String.valueOf(document.get("guide_total_count")));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error",Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "에러 발생. 네트워크 체크 요망",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    customDialog.setDialogListener(new CustomDialogStart.CustomDialogStartListener() {
                        @Override
                        public void onPositiveClicked(int addr) {
                            starTotalStar += addr;
                            starAverage = Double.parseDouble(String.valueOf(starTotalStar / (guideTotalCount + 1)));
                            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                            DocumentReference washingtonRef = firestore.collection("guide_user").document(guideId);
                            washingtonRef
                                    .update("guide_total_count", FieldValue.increment(1),
                                            "guide_aver_star",Math.round(starAverage*100)/100.0,
                                            "guide_total_star",starTotalStar)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            //Log.d(TAG, "DocumentSnapshot successfully updated!");
                                            Toast.makeText(getApplicationContext(), "평가 등록 완료",Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            //Log.w(TAG, "Error updating document", e);
                                        }
                                    });
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
                    Map<String, Object> city1 = new HashMap<>();
                    city1.put("state",now2);
                    city.put("id", tourId);
                    city.put("text", text);
                    city.put("time",now1.toString()+now2.toString() );
                    firestore.collection(tourId+"!@#"+guideId).document()
                            .set(city)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //finish();
                                    Toast.makeText(getApplicationContext(), "전송완료", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //Toast.makeText(getApplicationContext(), "아이디가 중복되었거나 다른 문제가 발생하였습니다.", Toast.LENGTH_LONG).show();
                                }
                            });

                    firestore.collection(tourId+"!@#"+guideId).document("state")
                            .set(city1)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //finish();
                                    //Toast.makeText(getApplicationContext(), "전송완료", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                   // Toast.makeText(getApplicationContext(), "아이디가 중복되었거나 다른 문제가 발생하였습니다.", Toast.LENGTH_LONG).show();
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



