package com.example.android.recycylerview2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TaskAdapter presAdapter;
    RecyclerView recyclerView;
    List<Task> tasks;

    final int REQUEST_CODE = 1;

    //Firebase init
//    FirebaseDatabase firebaseDatabase;
//    DatabaseReference firebase_tasks;
//    //Authentication
//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseAuth.AuthStateListener mAuthStateListener;
//    ChildEventListener childEventListener;
    //Constant
    private static final int RC_SIGN_IN = 1;
    private static final int RC_PHOTO_PICKER = 2;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        iniAdapter();
        iniRecyclerView();

        database = FirebaseDatabase.getInstance();
        System.out.println("Step 1");
//        myRef = database.getReference("message");
//        try {
//            myRef.setValue("Hello, World!");
//        }catch (Exception e){
//            System.out.println("Error uploading");
//        }


        //Firebase All here:
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
//                List<AuthUI.IdpConfig> providers = Arrays.asList(
//                        new AuthUI.IdpConfig.EmailBuilder().build(),
//                        new AuthUI.IdpConfig.GoogleBuilder().build());
//
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Toast.makeText(MainActivity.this, "You're now signed in. Welcome to FriendlyChat.", Toast.LENGTH_SHORT).show();
//                } else {
//                    // User is signed out
//                    startActivityForResult(
//                            AuthUI.getInstance()
//                                    .createSignInIntentBuilder().setAvailableProviders(providers)
//                                    .build(),
//                            RC_SIGN_IN);
//                }
//            }
//        };
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebase_tasks = firebaseDatabase.getReference();
    }

//    3.0 push data to firebase
    void pushToFirebase(Task task){
//        firebase_tasks.push().setValue(task);
    }

    //2.0 Get new data after input
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == REQUEST_CODE){
                if(resultCode ==RESULT_OK){
                    Task task = (Task)data.getSerializableExtra("Return");
                    assignText(task);
//                    pushToFirebase(task);
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
