package com.example.android.recycylerview2020;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Tasks;

import java.util.List;


public class TasksViewHolder extends RecyclerView.ViewHolder {
    private CardView cardView;
    private TextView taskNameView;
    private TextView taskTimeView;
    private TextView taskInfoView;
    private Context context;
    List<Task> tasks;

    @SuppressLint("WrongViewCast")
    public TasksViewHolder(View itemView, final Context context) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        taskNameView = (TextView) itemView.findViewById(R.id.task_name);
        taskTimeView = (TextView) itemView.findViewById(R.id.task_time);
        taskInfoView = (TextView) itemView.findViewById(R.id.task_info);
        this.context = context;

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), DoingTask.class);
                Task storedTask = tasks.get(getAdapterPosition());
                i.putExtra("taskInfo", tasks.get(getAdapterPosition()));
                Toast.makeText(context, "New Task added!", Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(i);

            }
        });
    }

    public TextView getTaskNameView() {
        return taskNameView;
    }

    public TextView getTaskTimeView() {
        return taskTimeView;
    }

    public TextView getTaskInfoView() {
        return taskInfoView;
    }

    public CardView getCardView(){
        return cardView;
    }

    public void iniTasks(List<Task> tasks){
        this.tasks = tasks;
    }
}


