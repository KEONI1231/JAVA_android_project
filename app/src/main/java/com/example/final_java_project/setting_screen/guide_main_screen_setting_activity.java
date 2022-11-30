package com.example.final_java_project.setting_screen;

import static com.example.final_java_project.R.id;
import static com.example.final_java_project.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.R;

public class guide_main_screen_setting_activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.guide_main_screen_setting);
        getSupportActionBar().setTitle("설정");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(layout.login_appbar);

        Intent getIntent = getIntent();
        String name = getIntent.getStringExtra("GuideName");
        String region = getIntent.getStringExtra("GuideRegion");
        String averScore = getIntent.getStringExtra("AverScore");
        String totalCount = getIntent.getStringExtra("TotalCounting");

        TextView nameTextView = findViewById(R.id.guide_name_text);
        TextView regionTextView = findViewById(R.id.guide_region_text);
        TextView averScoreTextView = findViewById(R.id.guide_aver_score);
        TextView totalCountingTextView = findViewById(R.id.guide_total_counting);

        System.out.println(name);
        System.out.println(region);
        System.out.println(averScore);
        System.out.println(totalCount);
        nameTextView.setText(name);
        regionTextView.setText(region);
        averScoreTextView.setText(averScore);
        totalCountingTextView.setText(totalCount);

    }
    public void onButtonClick(View view) {
        EditText nameEditText =(EditText) findViewById(R.id.guide_name_editText);
        EditText regionEditText = (EditText) findViewById(R.id.guide_region_editText);
        switch (view.getId()) {
            case id.try_change_info:

                Intent intent = new Intent();
                if(nameEditText.getText().toString().length()==0 ) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.",Toast.LENGTH_LONG).show();
                }else if( regionEditText.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "여행 지역을 입력해주세요.",Toast.LENGTH_LONG).show();
                }
                else {
                    String newName = nameEditText.getText().toString();
                    String newRegion = regionEditText.getText().toString();
                    intent.putExtra("NewGuideName", newName);
                    intent.putExtra("NewGuideRegion", newRegion);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }

}
