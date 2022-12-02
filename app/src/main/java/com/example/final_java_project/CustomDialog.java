package com.example.final_java_project;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {
    TextView txt_contents1;
    TextView txt_contents2;
    TextView txt_contents3;
    TextView txt_contents4;
    TextView txt_contents5;
    TextView txt_contents6;
    Button shutdownClick;

    public CustomDialog(@NonNull Context context,  int case_number) {
        super(context);
        setContentView(R.layout.dialog);

        if(case_number == 1) {
            txt_contents1 = findViewById(R.id.txt_contents1);
            txt_contents2 = findViewById(R.id.txt_contents2);
            txt_contents3 = findViewById(R.id.txt_contents3);
            txt_contents4 = findViewById(R.id.txt_contents4);
            txt_contents5 = findViewById(R.id.txt_contents5);
            txt_contents6 = findViewById(R.id.txt_contents6);
            txt_contents1.setText("상단의 내 정보는 여행객으로 로그인한 사람의 회원정보입니다.\n");
            txt_contents2.setText("상단의 내 정보는 오른쪽 아이콘 버튼을 클릭하여 회원 정보를 수정할수 있습니다.\n");
            txt_contents3.setText("현지 가이드 정보는 가이드로 등록되어있는 사람의 목록을 보여줍니다..\n");
            txt_contents4.setText("가이드 목록중 하나를 클릭하여 채팅 화면으로 넘어갑니다.\n");
            txt_contents5.setText("하단 네비게이션을 이용하여 화면을 이동.\n");
        }
        else if(case_number == 2) {
            txt_contents1 = findViewById(R.id.txt_contents1);
            txt_contents2 = findViewById(R.id.txt_contents2);
            txt_contents3 = findViewById(R.id.txt_contents3);
            txt_contents4 = findViewById(R.id.txt_contents4);
            txt_contents5 = findViewById(R.id.txt_contents5);
            txt_contents6 = findViewById(R.id.txt_contents6);
            txt_contents1.setText("회원가입 화면입니다.\n");
            txt_contents2.setText("가이드 등록 스위치가 true 일때 visibility를 visible로 하여 숨겨진 위젯을 보여줍니다.\n");
            txt_contents3.setText("스위치가 true면 여행객 계정이 아니라 가이드 계정으로 회원가입 합니다.\n");
            txt_contents4.setText("가이드로 화원가입하면 거주지 검색버튼을 클릭하여 현재 거주지를 설정하여야 합니다.\n");
            txt_contents5.setText("가이드의 프로필 메세지를 작성합니다.\n");
        }
        shutdownClick = findViewById(R.id.btn_shutdown);
        shutdownClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}