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
        getSupportActionBar().setCustomView(layout.main_setting_appbar);

        Intent getIntent = getIntent();
        String name = getIntent.getStringExtra("GuideName");
        String region = getIntent.getStringExtra("GuideRegion");
        String profileMsg = getIntent.getStringExtra("GuideProfileMsg");

        TextView nameTextView = findViewById(R.id.guide_name_text);
        TextView regionTextView = findViewById(R.id.guide_region_text);
        TextView profileMsgTextView = findViewById(id.profile_message);

        System.out.println(name);
        System.out.println(region);
        System.out.println(profileMsg);
        nameTextView.setText(name);
        regionTextView.setText(region);


    }
    public void onButtonClick(View view) {
        EditText nameEditText =(EditText) findViewById(R.id.guide_name_editText);
        EditText regionEditText = (EditText) findViewById(R.id.guide_region_editText);
        EditText profileMsgEditText = (EditText)findViewById(R.id.profile_message_editText);
        switch (view.getId()) {
            case id.try_change_info:

                Intent intent = new Intent();
                if(nameEditText.getText().toString().length()==0 ) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.",Toast.LENGTH_LONG).show();
                }else if( regionEditText.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "여행 지역을 입력해주세요.",Toast.LENGTH_LONG).show();
                }else if( profileMsgEditText.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "상태메세지를 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else {
                    String newName = nameEditText.getText().toString();
                    String newRegion = regionEditText.getText().toString();
                    String newProfileMsg = profileMsgEditText.getText().toString();
                    intent.putExtra("NewGuideName", newName);
                    intent.putExtra("NewGuideRegion", newRegion);
                    intent.putExtra("NewGuideProfileMsg", newProfileMsg);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }

}
