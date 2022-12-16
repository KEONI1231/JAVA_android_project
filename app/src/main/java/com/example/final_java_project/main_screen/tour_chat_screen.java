package com.example.final_java_project.main_screen;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_java_project.CustomDialogStart;
import com.example.final_java_project.R;
import com.example.final_java_project.list_adapter.CustomTourChatView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

public class tour_chat_screen extends AppCompatActivity {
    Handler mHandler;
    Socket socket;
    PrintWriter sendWriter;
    String ip = "211.62.179.135";
    int port = 8080;
    String tourId;
    String read=  "ㅁㅇㄴㄹㄴㅇㄹ";
    String sendMsg = "asdfsdaf";
    JSONObject json;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_chat);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.first_screen_appbar);
        ListView listView;
        listView = findViewById(R.id.listview);
        mHandler = new Handler();
        tourId = getIntent().getStringExtra("TourId");

        new Thread() {
            public void run() {
                try {
                    InetAddress serverAddr = InetAddress.getByName(ip);
                    socket = new Socket(serverAddr, port);
                    sendWriter = new PrintWriter(socket.getOutputStream());
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while(true){
                        read = input.readLine();

                       // System.out.println("TTTTTTTT"+read);
                        if(read!=null){
                              mHandler.post(new msgUpdate(json));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }}.start();

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        String[] title = {"김건휘(가이드)", "나", "김건휘(가이드)", "나", "김건휘(가이드)","김건휘(가이드)","나","김건휘(가이드)", "김건휘(가이드)"};

        String[] body_1 = {"안녕하세요 건휘님ㅎㅎ", "네 안녕하세요 가이드님", "어떤걸 도와드릴까요?",
                "미케해변 주변에 자리를 잡았는데\n이 주변에 현지인들 많이 가는술집이 있나요?", "아네 그 주변에 좋은 술집 많아요!!", "일단 그 주변에 칵테일 바가 있는데\n" +
                "여행객들도 많이 없고 거의 현지인들만 가요", "분위기가 많이 시끌시끌 한가요?","아니요 분위기는 많이 안시끄러워요ㅎㅎ", "치안도 좋아서 안심하셔도 됩니다"};
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.tour_chat_custom_listview, null);
        ArrayList<CustomTourChatView.ListData> listViewData = new ArrayList<>();
        int i = 0;
        for (int j = 0; j < 30; ++j) {
            CustomTourChatView.ListData listData = new CustomTourChatView.ListData();
            if (i >= 9) {
                i = 0;
            }
            listData.title = title[i];
            listData.body_1 = body_1[i];
            listViewData.add(listData);
            i = i + 1;
        }


        ListAdapter oAdapter = new CustomTourChatView(listViewData);
        listView.setAdapter(oAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickName = listViewData.get(position).title;

            }
        });
        listView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                listView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

    }

    public void onButtonClick(View view) throws JSONException {
        String text;
        switch (view.getId()) {
            case R.id.send_message:
                EditText editText = findViewById(R.id.chat_editText);
                text = editText.getText().toString();
                if (text.equals("//chat-server-clear")) {
                    CustomDialogStart customDialog;
                    customDialog = new CustomDialogStart(tour_chat_screen.this, 0);
                    String star = "";
                    customDialog.show();
              //      System.out.println(text);
                } else {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                read = "{\"type\":\"ENTER\", \"roomId\":\"944fd79f-5163-4c2c-9238-81c2e60af26d\",\"sender\":\"kimkim\",\"message\":\"asd\"}";
                                json = new JSONObject(read);
                                sendWriter.println(json);
                                sendWriter.flush();
                                System.out.println("asd");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
                break;
        }
    }
    class msgUpdate implements Runnable{
        private String msg;
        public msgUpdate(JSONObject str) {this.msg=str.toString();}

        @Override
        public void run() {
            System.out.println("sadfsadfdsafasdfasdfasdfasdfsadffsdfs");
        }
    }
}



