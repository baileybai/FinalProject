package com.example.android.recycylerview2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//This is a test hahaha
    TaskAdapter presAdapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        iniAdapter();
        iniRecyclerView();
    }

    //1.2 update recycler view with new tasks
    private void iniRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(presAdapter);
    }

    //1.1 initialize data in adapter
    private void iniAdapter() {
        List<Task> tasks;
        tasks = new ArrayList<>();
        presAdapter = new TaskAdapter(tasks, this);
        recyclerView.setAdapter(presAdapter);

    }

    //0.1 start the second activity, go to startNewActivity
    public void addNewTask() {
        Intent i = new Intent(this, StartNewActivity.class);
        startActivity(i);
    }

    //0.2 start the third activity, go to finishedTask display
    public void FinishedTasks(View view) {
    }
    //0.3 Create Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
