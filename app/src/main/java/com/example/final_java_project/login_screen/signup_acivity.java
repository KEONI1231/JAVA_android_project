package com.example.final_java_project.login_screen;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

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
    public void onButtonClick(View view) {
        EditText idEditText = (EditText) findViewById(R.id.signup_id);
        EditText pwEditText = (EditText)findViewById(R.id.signup_pw);
        EditText nameEditText = (EditText)findViewById(R.id.signup_name);
        switch (view.getId()) {

            case R.id.try_signup_btn:
                id = idEditText.getText().toString();
                pw = pwEditText.getText().toString();
                name = nameEditText.getText().toString();
                break;
            case R.id.search_region:
                /*Intent intent = new Intent(signup_acivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/

                Intent intentGuideLogin = new Intent(getApplicationContext(),
                        search_region_activity.class);
                startActivity(intentGuideLogin);
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
        }
    }

}