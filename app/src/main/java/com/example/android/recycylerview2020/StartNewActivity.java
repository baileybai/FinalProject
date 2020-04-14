package com.example.android.recycylerview2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class StartNewActivity extends AppCompatActivity {
    private String name;
    private String info;
    private int taskNeededTime;

    public StartNewActivity(String name, String info, int taskNeededTime) {
        this.name = name;
        this.info = info;
        this.taskNeededTime = taskNeededTime;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getTime() {
        return taskNeededTime;
    }

    public void setTime(int photoId) {
        this.taskNeededTime = taskNeededTime;
    }

//go from StartNewActivity to MainActivity
    public void GoBackToMainActivity(View view) {

    }
}
