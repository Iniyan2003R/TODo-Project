package com.example.todoproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;
    private TaskAdapterListener listener;

    public TaskAdapter(List<Task> tasks, TaskAdapterListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.taskName.setText(task.getName());

        // Set listener for clicking on the task to show details
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onTaskClick(task);  // New click listener for task item
        });

        holder.editButton.setOnClickListener(v -> {
            if (listener != null) listener.onEditTask(task);
        });
        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) listener.onDeleteTask(task);
        });
        holder.completeButton.setOnClickListener(v -> {
            if (listener != null) listener.onCompleteTask(task);
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        Button editButton, deleteButton, completeButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            completeButton = itemView.findViewById(R.id.completeButton);
        }
    }

    public interface TaskAdapterListener {
        void onTaskClick(Task task);  // New method for handling task click
        void onEditTask(Task task);
        void onDeleteTask(Task task);
        void onCompleteTask(Task task);
    }
}
