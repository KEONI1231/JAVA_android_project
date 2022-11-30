package com.example.final_java_project.login_screen;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.MainActivity;
import com.example.final_java_project.R;
import com.example.final_java_project.main_screen.tour_main_screen_activity;

public class signup_acivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.signup_appbar);
        Switch tourGuideCheck = findViewById(R.id.tour_guide_check);
        CheckState(tourGuideCheck);
        tourGuideCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckState(tourGuideCheck);
            }
        });
    }
    String id;
    String pw;
    String name;
    String region;
    String result;
    public void onButtonClick(View view) {
        EditText idEditText = (EditText) findViewById(R.id.signup_id);
        EditText pwEditText = (EditText)findViewById(R.id.signup_pw);
        EditText nameEditText = (EditText)findViewById(R.id.signup_name);
        switch (view.getId()) {
            case R.id.try_signup_btn:
                id = idEditText.getText().toString();
                pw = pwEditText.getText().toString();
                name = nameEditText.getText().toString();
                // result까지 넘겨줄 준비 완료
                break;
            case R.id.search_region:
                Intent intent = new Intent(getBaseContext(),
                        search_region_activity.class);
                launcher.launch(intent);
        }
    }
    public void CheckState(Switch tourGuideCheck) {
        TextView myRegionText = findViewById(R.id.my_region_text);
        Button searchRegion = findViewById(R.id.search_region);
        if(tourGuideCheck.isChecked()) {
            myRegionText.setVisibility(View.VISIBLE);
            searchRegion.setVisibility(View.VISIBLE);
        }
        else{
            myRegionText.setVisibility(View.GONE);
            searchRegion.setVisibility(View.GONE);
            TextView textView = (TextView)findViewById(R.id.my_region_text);
            result = "";
            textView.setText("선택한 거주지 : " + result);
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
                        textView.setText("선택한 거주지 : " + result);
                    }
                }
            });
}