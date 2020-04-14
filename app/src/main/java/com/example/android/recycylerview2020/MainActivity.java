package com.example.android.recycylerview2020;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
//This is a test haha
    TaskAdapter presAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presAdapter = new TaskAdapter(tasks, this);
        recyclerView.setAdapter(presAdapter);
    }

    public void addNewTask(View view) {
//start the second activity, go to startNewActivity
    }

    public void FinishedTasks(View view) {
    }
}
