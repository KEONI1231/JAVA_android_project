package com.example.final_java_project.login_screen;
import static com.example.final_java_project.R.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.CustomDialog;
import com.example.final_java_project.MainActivity;
import com.example.final_java_project.R;
import com.example.final_java_project.main_screen.tour_main_screen_activity;

public class signup_acivity extends AppCompatActivity {
    Switch tourGuideCheck;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.sign_up);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(layout.signup_appbar);
        tourGuideCheck = findViewById(R.id.tour_guide_check);
        CheckState(tourGuideCheck);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        tourGuideCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckState(tourGuideCheck);
            }
        });
    }
    String idText;
    String pwText;
    String nameText;
    String result;
    public void onButtonClick(View view) {
        EditText idEditText = (EditText) findViewById(R.id.signup_id);
        EditText pwEditText = (EditText)findViewById(R.id.signup_pw);
        EditText nameEditText = (EditText)findViewById(R.id.signup_name);

        switch (view.getId()) {
            case R.id.try_signup_btn:
                idText = idEditText.getText().toString();
                pwText = pwEditText.getText().toString();
                nameText = nameEditText.getText().toString();

                if(idText.length() == 0 || pwText.length() == 0 || nameText.length()  == 0) {
                    Toast.makeText(getApplicationContext(), "ID, PW, 이름을 모두 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else if(tourGuideCheck.isChecked() == true && result.length() == 0) {
                    Toast.makeText(getApplicationContext(), "가이드로 회원가입 하려면 거주지를 입력해주세요.", Toast.LENGTH_LONG).show();
                }
                else {
                    finish();
                    Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_LONG).show();

                    //restapi 통신
                }
                // result까지 넘겨줄 준비 완료
                break;
            case R.id.queston_button:
                CustomDialog customDialog;
                customDialog = new CustomDialog(signup_acivity.this,2);
                customDialog.show();
                break;
            case R.id.search_region:
                Intent intent = new Intent(getBaseContext(),
                        search_region_activity.class);
                launcher.launch(intent);
                break;


        }
    }
    public void CheckState(Switch tourGuideCheck) {
        TextView myRegionText = findViewById(R.id.my_region_text);
        TextView myProfileText = findViewById(R.id.my_profile_text);
        EditText myProfileEditText = findViewById(R.id.my_profile_message_editText);
        Button searchRegion = findViewById(R.id.search_region);

        if(tourGuideCheck.isChecked()) {
            myRegionText.setVisibility(View.VISIBLE);
            searchRegion.setVisibility(View.VISIBLE);
            myProfileText.setVisibility(View.VISIBLE);
            myProfileEditText.setVisibility(View.VISIBLE);
        }
        else{
            myRegionText.setVisibility(View.GONE);
            searchRegion.setVisibility(View.GONE);
            myProfileText.setVisibility(View.GONE);
            myProfileEditText.setVisibility(View.GONE);
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