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

    public CustomDialog(@NonNull Context context, int case_number) {
        super(context);
        setContentView(R.layout.dialog);

        if (case_number == 1) {
            txt_contents1 = findViewById(R.id.txt_contents1);
            txt_contents2 = findViewById(R.id.txt_contents2);
            txt_contents3 = findViewById(R.id.txt_contents3);
            txt_contents4 = findViewById(R.id.txt_contents4);
            txt_contents5 = findViewById(R.id.txt_contents5);
            txt_contents6 = findViewById(R.id.txt_contents6);
            txt_contents1.setText("상단의 내 정보는 여행객으로 로그인한 사람의 회원정보입니다.\n");
            txt_contents2.setText("상단의 내 정보 오른쪽 아이콘 버튼(설정 아이콘)을 클릭하여 회원 정보를 수정할수 있습니다.\n");
            txt_contents3.setText("현지 가이드 정보는 가이드로 등록되어있는 사람의 목록을 보여줍니다..\n");
            txt_contents4.setText("가이드 목록중 하나를 클릭하여 채팅 화면으로 넘어갑니다.\n");
            txt_contents5.setText("하단 네비게이션을 이용하여 화면을 이동.\n");
        } else if (case_number == 2) {
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
        } else if (case_number == 3) {
            txt_contents1 = findViewById(R.id.txt_contents1);
            txt_contents2 = findViewById(R.id.txt_contents2);
            txt_contents3 = findViewById(R.id.txt_contents3);
            txt_contents4 = findViewById(R.id.txt_contents4);
            txt_contents5 = findViewById(R.id.txt_contents5);
            txt_contents6 = findViewById(R.id.txt_contents6);
            txt_contents1.setText("가이드 메인 화면입니다.\n");
            txt_contents2.setText("상단의 내 정보는 가이드로 로그인한 사람의 간단한 회원정보입니다.\n");
            txt_contents3.setText("상단의 오른쪽 설정 아이콘을 클릭하여 내 정보를 변경할수 있습니다..\n");
            txt_contents4.setText("진행중인 채팅목록은 현재 채팅중인 여행객들의 채팅방 목록입니다.\n");
            txt_contents5.setText("해당 채팅방을 클릭하여 클릭한 여행객 회원과 채팅을 진행할 수 있습니다..\n");
        } else if (case_number == 0) {
            txt_contents1 = findViewById(R.id.txt_contents1);
            txt_contents2 = findViewById(R.id.txt_contents2);
            txt_contents3 = findViewById(R.id.txt_contents3);
            txt_contents4 = findViewById(R.id.txt_contents4);
            txt_contents5 = findViewById(R.id.txt_contents5);
            txt_contents6 = findViewById(R.id.txt_contents6);
            txt_contents1.setText("앱의 첫번째 화면입니다.\n");
            txt_contents2.setText("하단의 가이드 로그인, 여행객 로그인, 회원가입중 하나를 선택합니다.\n");
            txt_contents3.setText("상단의 오른쪽 설정 아이콘을 클릭하여 내 정보를 변경할수 있습니다..\n");
            txt_contents4.setText("앱의 화면 수 : 현재 화면, 회원가입, 거주지 검색, 다이얼러그(2종류), 로그인(여행객 로그인, 가이드 로그인), 여행객 메인 화면, 여행객 정보수정 화면, 여행객 채팅 화면, 가이드 메인 화면, 가이드 정보 수정 화면, 가이드 채팅 화면 .\n");
            txt_contents5.setText("기능 : 회원가입(가이드, 여행객), 로그인(가이드, 여행객), 거주지 검색, 채팅, 화면정보 설명 다이얼러그, 계정 정보 수정, 채팅 종료, 가이드 별점 평가.\n");
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