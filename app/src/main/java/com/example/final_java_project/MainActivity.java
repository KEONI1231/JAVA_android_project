package com.example.final_java_project;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.final_java_project.login_screen.guide_login_activity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Safe Journey");
    }
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.login_guide_btn:
                Intent intentGuideLogin = new Intent(getApplicationContext(), guide_login_activity.class);
                startActivity(intentGuideLogin);
                break;
            case R.id.login_tourist_btn:
                Intent intentTouristLogin = new Intent(getApplicationContext(), guide_login_activity.class);
                startActivity(intentTouristLogin);
                break;
        }
    }
}