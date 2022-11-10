package com.example.final_java_project;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Intent intent = new Intent(getApplicationContext(), menu_activity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                break;
        }
    }
}