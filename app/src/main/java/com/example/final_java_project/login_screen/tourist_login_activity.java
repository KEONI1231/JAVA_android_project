package com.example.final_java_project.login_screen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.R;
import com.example.final_java_project.main_screen.tour_main_screen_activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class tourist_login_activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tourist_login);
        //앱바 텍스트
        getSupportActionBar().setTitle("여행객 로그인");
        // id, pw 텍스트 색조정
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.login_appbar);
    }
    public void onButtonClick(View view) {
        EditText idEditText = findViewById(R.id.id_editText);
        EditText pwEditText = findViewById(R.id.pw_editText);
        String id = idEditText.getText().toString();
        String pw = pwEditText.getText().toString();
        final String[] firestoreId = {""};
        final String[] firestorePw = {""};
        final String[] myRegion = {""};
        switch (view.getId()) {
            case R.id.login_try_btn:
                if(idEditText.getText().toString().length() == 0 || pwEditText.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "id, pw 모두 입력해주세요.",Toast.LENGTH_LONG).show();
                }
                else {
                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    DocumentReference docRef = firestore.collection("tour_user").document(idEditText.getText().toString());
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    firestoreId[0] = document.get("id").toString();
                                    firestorePw[0] = document.get("pw").toString();
                                    myRegion[0] = document.get("trip_region").toString();
                                    if(firestoreId[0].equals(id) && firestorePw[0].equals(pw)) {
                                        Toast.makeText(getApplicationContext(), "로그인 성공",Toast.LENGTH_LONG).show();
                                        Intent intentTourLogin = new Intent(getApplicationContext(),
                                                tour_main_screen_activity.class);
                                        intentTourLogin.putExtra("TourId",idEditText.getText().toString());
                                        intentTourLogin.putExtra("TripRegion",myRegion[0]);
                                        startActivity(intentTourLogin);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "id또는 pw가 일치하지 않습니다..",Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "id또는 pw가 일치하지 않습니다..",Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "에러 발생. 네트워크 체크 요망",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                break;

        }
    }
}
