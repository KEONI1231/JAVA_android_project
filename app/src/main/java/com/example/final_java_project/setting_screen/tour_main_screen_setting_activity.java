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
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.R;

public class tour_main_screen_setting_activity extends AppCompatActivity {
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
        String period = getIntent.getStringExtra("Period");

        TextView nameTextView = findViewById(id.user_name_text);
        TextView regionTextView = findViewById(id.user_region_text);

        nameTextView.setText(name);
        regionTextView.setText(region);
    }
    public void onButtonClick(View view) {
        EditText nameEditText = (EditText) findViewById(id.name_editText);
        EditText regionEditText = (EditText) findViewById(id.trip_region_editText);
        switch (view.getId()) {
            case R.id.try_change_info:

                Intent intent = new Intent();
                if(nameEditText.getText().toString().length()==0 ) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.",Toast.LENGTH_LONG).show();

                }else if( regionEditText.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "여행 지역을 입력해주세요.",Toast.LENGTH_LONG).show();
                }
                else {
                    String newName = nameEditText.getText().toString();

                    String newRegion = regionEditText.getText().toString();
                    intent.putExtra("NewName", newName);

                    intent.putExtra("NewRegion", newRegion);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }

}
