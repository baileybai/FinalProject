package com.example.android.recycylerview2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DoingTask extends AppCompatActivity {

    Task task;
    TextView name, time;
    LinearLayout buttonField;
    String timeDisplay;
//    int taskPosition;

    final int FINISHED_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_task);
        Intent m = getIntent();
        task = (Task) getIntent().getSerializableExtra("taskInfo");
//        taskPosition = (int)getIntent().getSerializableExtra("taskPosition");

        name = (TextView) findViewById(R.id.doing_field_task_name);
        updateName();
        time = (TextView) findViewById(R.id.doing_field_task_time);
        updateTime(String.valueOf(task.getTime()));
        buttonField = (LinearLayout) findViewById(R.id.doing_field_task_button_field);
        buttonFieldVisible(false);

        startCountingDown();
    }

    private void startCountingDown() {
        CountDownTimer timer = new CountDownTimer(convertTime(), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTime(getTimeDisplay(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                buttonFieldVisible(true);
            }
        }.start();


    }

    private String getTimeDisplay(long millisUntilFinished) {
        int timeInSeconds = (int) millisUntilFinished / 1000;
        int minuteLeft = timeInSeconds / 60;
        int secondLeft = timeInSeconds % 60;
        String timeForDisplay = minuteLeft + ": ";
        if(secondLeft<10){
            timeForDisplay += "0";
        }
        timeForDisplay += secondLeft;
        return timeForDisplay;
    }


    public long convertTime() {//convert time to milisecond
        float timeDbug = task.getTime() * 60 * 1000;
        return (long)timeDbug;
    }

    public void updateName() {
        name.setText(task.getName());
    }

    public void updateTime(String timeDisplay) {
        time.setText(timeDisplay);
    }

    public void buttonFieldVisible(boolean check) {
        if (check) {
            buttonField.setVisibility(View.VISIBLE);
        } else {
            buttonField.setVisibility(View.INVISIBLE);
        }
    }

}
