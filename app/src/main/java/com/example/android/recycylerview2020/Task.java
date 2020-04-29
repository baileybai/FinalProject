package com.example.android.recycylerview2020;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class Task implements Serializable {
    private String name;
    private String info;
    private float taskNeededTime;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Task(){

    }

    public Task(String name, String info, float taskNeededTime) {
        this.name = name;
        this.info = info;
        this.taskNeededTime = taskNeededTime;
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

    public float getTaskNeededTime() {
        return taskNeededTime;
    }

    public void setTaskNeededTime(float taskNeededTime) {
        this.taskNeededTime = taskNeededTime;
    }
}
