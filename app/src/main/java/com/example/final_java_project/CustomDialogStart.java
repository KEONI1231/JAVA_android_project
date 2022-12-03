package com.example.final_java_project;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class CustomDialogStart extends Dialog {
    RadioButton one;
    RadioButton two;
    RadioButton three;
    RadioButton four;
    RadioButton five;
    RadioGroup radioGroup;
    Button shutdownClick;
    int star_number = 0;
    public interface CustomDialogStartListener{
        static void clickBtn(String data) {

        }
    }
    public CustomDialogStart(@NonNull Context context, int case_number) {
        super(context);
        setContentView(R.layout.star_dialog);
        one = (RadioButton) findViewById(R.id.one);
        two = (RadioButton)findViewById(R.id.two);
        three = (RadioButton)findViewById(R.id.three);
        four = (RadioButton)findViewById(R.id.four);
        five = (RadioButton)findViewById(R.id.five);
        radioGroup = findViewById(R.id.radio_group);



        shutdownClick = findViewById(R.id.btn_shutdown);

        shutdownClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(star_number == 0) {
                    Toast.makeText(context.getApplicationContext(), "가이드 평점을 등록해주세요.", Toast.LENGTH_LONG).show();
                }
                else {
                    CustomDialogStartListener.clickBtn("clicked");
                    dismiss();
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.one:
                        star_number = 1;
                        break;
                    case R.id.two:
                        star_number = 2;
                        break;
                    case R.id.three:
                        star_number = 3;
                        break;
                    case R.id.four:
                        star_number = 4;
                        break;
                    case R.id.five:
                        star_number = 5;
                        break;
                }
            }
        });


    }
}
