package com.example.android.recycylerview2020;

public class Task {
    private String name;
    private String info;
    private float taskNeededTime;

    public Task(String name, String info, int taskNeededTime) {
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
