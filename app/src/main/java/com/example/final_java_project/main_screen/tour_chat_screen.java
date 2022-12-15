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

        String[] title = {"김건휘(가이드)", "나", "김건휘(가이드)", "나", "김건휘(가이드)"};
        ;
        String[] body_1 = {"빠르고 정확하게, 친절하게 도와드립니다!!!", "현지인 이다! 나는!! 항쿡말 자알 몯해!@!!", "완벽주의자. 기적의 60키로 감량",
                "왈왈!!!왈왈와로알!!!와라라랄!!!왈!", "밤톨아 밥먹자~"};
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.tour_chat_custom_listview, null);
        ArrayList<CustomTourChatView.ListData> listViewData = new ArrayList<>();
        int i = 0;
        for (int j = 0; j < 30; ++j) {
            CustomTourChatView.ListData listData = new CustomTourChatView.ListData();
            if (i >= 5) {
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



