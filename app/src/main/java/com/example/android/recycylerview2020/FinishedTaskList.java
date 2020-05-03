package com.example.android.recycylerview2020;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FinishedTaskList extends AppCompatActivity {

    TaskAdapter finishedTaskAdapter;
    RecyclerView recyclerView;
    public List<Task> tasks;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbr;
    ChildEventListener childEventListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_task_list);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_in_finished);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tasks = new ArrayList<>();

        iniAdapter(tasks);
        iniRecyclerView();

        firebaseDatabase = FirebaseDatabase.getInstance();
        dbr = firebaseDatabase.getReference().child("fin_Task");

        attachDataBaseReadListenser();

    }


    //1.2 update recycler view with new tasks
    private void iniRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(finishedTaskAdapter);
    }

    //1.1 initialize data in adapter
    private void iniAdapter(List<Task> tasks) {
        finishedTaskAdapter = new TaskAdapter(tasks, this, 2);
    }

    //2.4 Remove from list
    public void removeTask(String key){
        dbr.child(key).removeValue();
    }
    //2.3 refresh the whole array
    void updateTheWholeArray(){
        dbr.removeEventListener(childEventListener);
        tasks.clear();
        dbr.addChildEventListener(childEventListener);
    }

    //2.2 Assign text for display
    private void assignText(Task aReturn) {
        tasks.add(aReturn);
    }

    //2.1 add listener
    void attachDataBaseReadListenser() {
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Task task = dataSnapshot.getValue(Task.class);
                    task.setKey(dataSnapshot.getKey());
                    assignText(task);
                    iniRecyclerView();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    updateTheWholeArray();
                    iniRecyclerView();
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Toast.makeText(MainActivity.this, "Error in action", Toast.LENGTH_SHORT).show();
                }
            };
            dbr.addChildEventListener(childEventListener);
        }
    }

    public void clearAllTask(View view) {
        dbr.removeValue();
    }
}
