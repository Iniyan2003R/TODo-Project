package com.example.todoproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    private TextView taskNameTextView, taskDescriptionTextView, taskDateTextView, taskTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        taskNameTextView = findViewById(R.id.taskNameTextView);
        taskDescriptionTextView = findViewById(R.id.taskDescriptionTextView);
        taskDateTextView = findViewById(R.id.taskDateTextView);
        taskTimeTextView = findViewById(R.id.taskTimeTextView);

        // Get task from intent
        int taskId = getIntent().getIntExtra("task_id", -1);
        Task task = new SQLiteHelper(this).getTaskById(taskId);

        if (task != null) {
            // Set task details
            taskNameTextView.setText(task.getName());
            taskDescriptionTextView.setText(task.getDescription());

            // Format and set the date and time
            String formattedDate = task.getDay() + "/" + task.getMonth() + "/" + task.getYear();
            String formattedTime = task.getHour() + ":" + task.getMinute();

            taskDateTextView.setText("Date: " + formattedDate);
            taskTimeTextView.setText("Time: " + formattedTime);
        }
    }

    // Handle back button click in action bar
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();  // Handle back press
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
