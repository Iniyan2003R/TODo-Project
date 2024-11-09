package com.example.todoproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    private TextView taskNameTextView, taskDescriptionTextView, taskDeadlineTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        taskNameTextView = findViewById(R.id.taskNameTextView);
        taskDescriptionTextView = findViewById(R.id.taskDescriptionTextView);
        taskDeadlineTextView = findViewById(R.id.taskDeadlineTextView);

        int taskId = getIntent().getIntExtra("task_id", -1);

        if (taskId != -1) {
            SQLiteHelper dbHelper = new SQLiteHelper(this);
            Task task = dbHelper.getTaskById(taskId);

            // Display task details
            taskNameTextView.setText(task.getName());
            taskDescriptionTextView.setText(task.getDescription());
            taskDeadlineTextView.setText(task.getFormattedDeadline());
        }
    }
}
