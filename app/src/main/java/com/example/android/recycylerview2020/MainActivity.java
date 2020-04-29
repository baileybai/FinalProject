package com.example.android.recycylerview2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//0.1 start the second activity, go to startNewActivity
//0.2 start the third activity, go to finishedTask display
//0.3 Create Menu

//1.2 update recycler view with new tasks

//2.0 Get new data after input
//2.1 Assign text for display

//3.0 push data to firebase
//3.1 add listener
//3.2 refresh the whole array

//4.1 RESUME
//4.2 return from intent
//4.3 delete finished task
public class MainActivity extends AppCompatActivity {
    TaskAdapter presAdapter;
    RecyclerView recyclerView;
    public List<Task> tasks;

    public final int REQUEST_CODE = 1;
    public final int FINISH_CODE = 2;

    //Firebase init
    FirebaseDatabase firebaseDatabase;
    DatabaseReference un_FirebaseRef_tasks, finished_firebaseRef_tasks;
    //Authentication
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    ChildEventListener childEventListener;
    //Constant
    private static final int RC_SIGN_IN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tasks = new ArrayList<>();

        iniAdapter(tasks);
        iniRecyclerView();


        //Firebase All here:
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.GoogleBuilder().build());

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(MainActivity.this, "You're now signed in. Welcome to FriendlyChat.", Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder().setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };

        firebaseDatabase = FirebaseDatabase.getInstance();
        un_FirebaseRef_tasks = firebaseDatabase.getReference().child("un_Task");
        finished_firebaseRef_tasks = firebaseDatabase.getReference().child("fin_Task");

        attachDataBaseReadListenser();
    }


    //4.3 delete finished task
    public void finishingTask(String key) {
        un_FirebaseRef_tasks.child(key).removeValue();
    }

    //4.2 return from intent
    private void returnFromIntent() {
        if (getIntent() != null) {
            int result = (int) getIntent().getSerializableExtra("result");
            if (result == FINISH_CODE) {
                finishingTask((String) getIntent().getSerializableExtra("key"));
            }
        }
    }


    //4.1 RESUME
    protected void onResume() {
        super.onResume();

        attachDataBaseReadListenser();
        iniAdapter(tasks);
    }


    //3.2 refresh the whole array
    void updateTheWholeArray(){
        un_FirebaseRef_tasks.removeEventListener(childEventListener);
        tasks.clear();
        un_FirebaseRef_tasks.addChildEventListener(childEventListener);
    }

    //3.1 add listener
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
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Toast.makeText(MainActivity.this, "Error in action", Toast.LENGTH_SHORT).show();
                }
            };
            un_FirebaseRef_tasks.addChildEventListener(childEventListener);
        }
    }



    //3.0 push data to firebase
    void pushToFirebase(Task task) {
        un_FirebaseRef_tasks.push().setValue(task);
    }

    //2.0 Get new data after input
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Task task = (Task) data.getSerializableExtra("Return");
                pushToFirebase(task);
            }
        }else if(requestCode == FINISH_CODE){
            if(resultCode == RESULT_OK){
                finishingTask((String) data.getSerializableExtra("key"));
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
    private void iniAdapter(List<Task> tasks) {
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
        Intent i = new Intent(this, FinishedTaskList.class);
        startActivity(i);
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
