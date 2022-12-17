package com.example.final_java_project.setting_screen;

import static com.example.final_java_project.R.id;
import static com.example.final_java_project.R.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import com.example.final_java_project.R;
import com.example.final_java_project.login_screen.search_region_activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class guide_main_screen_setting_activity extends AppCompatActivity {
    String result = "";
    String guideId = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.guide_main_screen_setting);
        getSupportActionBar().setTitle("설정");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(layout.main_setting_appbar);
        Intent getIntent = getIntent();
        String name = getIntent.getStringExtra("GuideName");
        String region = getIntent.getStringExtra("GuideRegion");
        guideId = getIntent.getStringExtra("GuideId");
        TextView nameTextView = findViewById(R.id.guide_name_text);
        TextView regionTextView = findViewById(R.id.guide_region_text);
        nameTextView.setText(name);
        regionTextView.setText(region);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference docRef = firestore.collection("guide_user").document(guideId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        TextView guideAverView = findViewById(R.id.guide_aver_score);
                        TextView guideTotalCountView = findViewById(R.id.guide_total_counting);
                        TextView guideProfileView = findViewById(R.id.profile_message);

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
    }
    public void onButtonClick(View view) {
        EditText nameEditText =(EditText) findViewById(R.id.guide_name_editText);
        EditText profileMsgEditText = (EditText)findViewById(R.id.profile_message_editText);
        switch (view.getId()) {
            case id.try_change_info:
                Intent intent = new Intent();
                if(nameEditText.getText().toString().length()==0 ) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.",Toast.LENGTH_LONG).show();
                }else if( result.length() == 0) {
                    Toast.makeText(getApplicationContext(), "현재 거주지역을 입력해주세요.",Toast.LENGTH_LONG).show();
                }else if( profileMsgEditText.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "상태메세지를 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else {
                    String newName = nameEditText.getText().toString();
                    String newProfileMsg = profileMsgEditText.getText().toString();
                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    DocumentReference washingtonRef = firestore.collection("guide_user").document(guideId);

                    washingtonRef
                            .update("name", newName, "guide_profile_message", newProfileMsg,"guide_region",result)
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
                    String newRegion = result;
                    intent.putExtra("NewGuideName", newName);
                    intent.putExtra("NewGuideRegion", newRegion);
                    intent.putExtra("NewGuideProfileMsg", newProfileMsg);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.search_region:
                Intent intent1 = new Intent(getBaseContext(),
                        search_region_activity.class);
                launcher.launch(intent1);
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
                        result = intent.getStringExtra ("result");
                        TextView textView = findViewById(R.id.my_region_text);
                        textView.setText(result);
                        textView.setVisibility(View.VISIBLE);
                    }
                }
            });
}
