package com.example.android.recycylerview2020;

import java.io.Serializable;

public class Task implements Serializable {
    private String name;
    private String info;
    private float taskNeededTime;

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

    public float getTime() {
        return taskNeededTime;
    }

    public void setTime(int photoId) {
        this.taskNeededTime = taskNeededTime;
    }
}
