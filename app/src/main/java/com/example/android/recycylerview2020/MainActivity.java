package com.example.android.recycylerview2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
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
    List<Task> tasks;

    final int REQUEST_CODE = 1;


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
    //2.0 Get new data after input
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == REQUEST_CODE){
                if(resultCode ==RESULT_OK){
                    assignText((Task)data.getSerializableExtra("Return"));
                }
            }
    }

    //2.1 Assign text for display
    private void assignText(Task aReturn) {
        tasks.add(aReturn);
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
        tasks = new ArrayList<>();
        presAdapter = new TaskAdapter(tasks, this);
        recyclerView.setAdapter(presAdapter);

    }

    //0.1 start the second activity, go to startNewActivity
    public void addNewTask() {
        Intent i = new Intent(this, StartNewActivity.class);
        startActivityForResult(i, REQUEST_CODE);
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
                addNewTask();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
