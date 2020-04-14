package com.example.android.recycylerview2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TasksViewHolder> {
    private List<Task> tasks;
    private Context context;

    public TaskAdapter(List<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_task, parent, false);
        return new TasksViewHolder(view, context);
    }


    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.getTaskNameView().setText(task.getName());
        holder.getTaskTimeView().setText(task.getTime());
        holder.getTaskInfoView().setText(task.getInfo());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

}

