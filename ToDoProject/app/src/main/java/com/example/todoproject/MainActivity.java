package com.example.todoproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.TaskAdapterListener {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new SQLiteHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadTasks();

        // FloatingActionButton to add a new task
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class); // Open the Add Task screen
            startActivity(intent);
        });

        // History Button - Navigate to Task History
        findViewById(R.id.historyButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TaskHistoryActivity.class);
            startActivity(intent);
        });
    }

    // Method to load tasks from the database and display in RecyclerView
    private void loadTasks() {
        List<Task> tasks = dbHelper.getAllTasks();
        adapter = new TaskAdapter(tasks, this); // Pass listener for task interactions (edit, delete, complete)
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTaskClick(Task task) {
        // Code to show task details
        Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
        intent.putExtra("task_id", task.getId());  // Pass the task ID to the details activity
        startActivity(intent);
    }

    @Override
    public void onEditTask(Task task) {
        // Code to edit task
        Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
        intent.putExtra("task_id", task.getId());
        startActivity(intent);
    }

    @Override
    public void onDeleteTask(Task task) {
        // Code to delete task
        dbHelper.deleteTask(task.getId());
        loadTasks(); // Reload tasks after deletion
        Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleteTask(Task task) {
        // Code to mark task as completed
        dbHelper.updateTaskCompletion(task.getId(), true);
        loadTasks(); // Reload tasks after marking as completed
        Toast.makeText(this, "Task marked as completed", Toast.LENGTH_SHORT).show();
    }
}
