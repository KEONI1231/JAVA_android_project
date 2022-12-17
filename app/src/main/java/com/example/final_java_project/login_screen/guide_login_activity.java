package com.example.final_java_project.login_screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.final_java_project.R;
import com.example.final_java_project.main_screen.guide_main_screen_activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class guide_login_activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_login);
        getSupportActionBar().setTitle("가이드 로그인");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.login_appbar);
    }
    public void onButtonClick(View view) {
        EditText idEditText = findViewById(R.id.id_editText);
        EditText pwEditText = findViewById(R.id.pw_editText);
        String[] firestoreId = {""};
        String[] firestorePw = {""};
        String[] myRegion = {""};
        String id = idEditText.getText().toString();
        String pw = pwEditText.getText().toString();
        switch (view.getId()) {
            case R.id.login_try_btn:
                if(idEditText.getText().toString().length() == 0 || pwEditText.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "id, pw 모두 입력해주세요.",Toast.LENGTH_LONG).show();
                }
                else {
                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    DocumentReference docRef = firestore.collection("guide_user").document(idEditText.getText().toString());
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    firestoreId[0] = document.get("id").toString();
                                    firestorePw[0] = document.get("pw").toString();
                                    myRegion[0] = document.get("guide_region").toString();
                                    if(firestoreId[0].equals(id) && firestorePw[0].equals(pw)) {
                                        Toast.makeText(getApplicationContext(), "로그인 성공",Toast.LENGTH_LONG).show();
                                        Intent intentGuideLogin = new Intent(getApplicationContext(),
                                                guide_main_screen_activity.class);
                                        intentGuideLogin.putExtra("GuideId",id);
                                        intentGuideLogin.putExtra("GuideRegion",myRegion[0]);
                                        startActivity(intentGuideLogin);
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