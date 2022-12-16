package com.example.final_java_project.setting_screen;

import static com.example.final_java_project.R.*;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class tour_main_screen_setting_activity extends AppCompatActivity {
    String result="";
    String tourId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.tour_main_screen_setting);
        getSupportActionBar().setTitle("설정");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(layout.main_setting_appbar);

        Intent getIntent = getIntent();
        String name = getIntent.getStringExtra("Name");
        String region = getIntent.getStringExtra("Region");
        //String period = getIntent.getStringExtra("Period");
        tourId = getIntent.getStringExtra("TourId");
        TextView nameTextView = findViewById(id.user_name_text);
        TextView regionTextView = findViewById(id.user_region_text);
        System.out.println(tourId);

        nameTextView.setText(name);
        regionTextView.setText(region);
    }

    public void onButtonClick(View view) {
        EditText nameEditText = (EditText) findViewById(id.name_editText);

        switch (view.getId()) {
            case R.id.try_change_info:

                Intent intent = new Intent();
                if (nameEditText.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_LONG).show();

                } else if (result.length() == 0) {
                    Toast.makeText(getApplicationContext(), "여행 지역을 입력해주세요.", Toast.LENGTH_LONG).show();
                } else {
                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    DocumentReference washingtonRef = firestore.collection("tour_user").document(tourId);
                    washingtonRef
                            .update("trip_region", result, "name", nameEditText.getText().toString())
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
                    String newName = nameEditText.getText().toString();

                    String newRegion = result;
                    intent.putExtra("NewName", newName);

                    intent.putExtra("NewRegion", newRegion);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case id.search_region:
                Intent intent1 = new Intent(getBaseContext(),
                        search_region_activity.class);
                launcher.launch(intent1);
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
                        result = intent.getStringExtra("result");

                        TextView textView = findViewById(R.id.my_region_text);
                        textView.setText(result);
                        textView.setVisibility(View.VISIBLE);

                    }
                }
            });

}
