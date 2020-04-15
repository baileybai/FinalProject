package com.example.android.recycylerview2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StartNewActivity extends AppCompatActivity {

    EditText name, time, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new);
        name = (EditText) findViewById(R.id.activity_name);
        time = (EditText) findViewById(R.id.activity_time);
        description = (EditText) findViewById(R.id.activity_description);
    }


    public void addTask(View view) {
        System.out.println(inputCheck(getValue(name), getValue(description), getValue(time)));
        if (inputCheck(getValue(name), getValue(description), getValue(time)) == true) {

            Task newTask = new Task(getValue(name), getValue(description), getTime(time));
            Intent data = new Intent();
            data.putExtra("Return", newTask);
            setResult(RESULT_OK, data);
            finish();
        }
    }

    private boolean inputCheck(String name, String des, String time) {
        if (name.trim().length() == 0) {
            Toast.makeText(this,"Name for the task is needed", Toast.LENGTH_SHORT).show();
            return false;
        } else if (time.trim().length() == 0) {
            Toast.makeText(this, "How long you expect to finish?", Toast.LENGTH_SHORT).show();
            return false;
        } else if (des.trim().length() == 0) {
            description.setText("");
            return true;
        }
        return true;
    }

    private String getValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    private float getTime(EditText editText) {
        return Float.parseFloat(editText.getText().toString());
    }
}
