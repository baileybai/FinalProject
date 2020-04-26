package com.example.android.recycylerview2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;

public class DoingTask extends AppCompatActivity {

    Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_task);
        Intent m = getIntent();
        task = (Task)getIntent().getParcelableExtra("taskInfo");
    }
}
