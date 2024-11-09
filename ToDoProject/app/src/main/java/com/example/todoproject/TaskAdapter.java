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
    private boolean isHistoryPage;

    // Constructor added with isHistoryPage parameter
    public TaskAdapter(List<Task> tasks, TaskAdapterListener listener, boolean isHistoryPage) {
        this.tasks = tasks;
        this.listener = listener;
        this.isHistoryPage = isHistoryPage;  // Track if we're in the history page
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

        // Display task number (position + 1) before task name
        holder.taskNumber.setText(String.valueOf(position + 1));  // Add task number

        // Display task name
        holder.taskName.setText(task.getName());

        // If it's the history page, hide the edit, delete, and complete buttons
        if (isHistoryPage) {
            holder.editButton.setVisibility(View.GONE);
            holder.deleteButton.setVisibility(View.GONE);
            holder.completeButton.setVisibility(View.GONE);
        } else {
            // Otherwise, set listeners for the buttons as usual
            holder.itemView.setOnClickListener(v -> {
                if (listener != null) listener.onTaskClick(task);
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
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskNumber, taskName;
        Button editButton, deleteButton, completeButton;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskNumber = itemView.findViewById(R.id.taskNumber);  // Bind the task number view
            taskName = itemView.findViewById(R.id.taskName);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            completeButton = itemView.findViewById(R.id.completeButton);
        }
    }

    public interface TaskAdapterListener {
        void onTaskClick(Task task);
        void onEditTask(Task task);
        void onDeleteTask(Task task);
        void onCompleteTask(Task task);
    }
}
