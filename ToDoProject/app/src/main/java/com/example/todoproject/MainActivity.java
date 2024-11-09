package com.example.todoproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            // Code to add a new task (e.g., open dialog or activity)
        });

        findViewById(R.id.historyButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TaskHistoryActivity.class);
            startActivity(intent);
        });
    }

    private void loadTasks() {
        List<Task> tasks = dbHelper.getTasks();
        adapter = new TaskAdapter(tasks, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onEditTask(Task task) {
        // Code to edit task
    }

    @Override
    public void onDeleteTask(Task task) {
        dbHelper.deleteTask(task.getId());
        loadTasks();
    }

    @Override
    public void onCompleteTask(Task task) {
        dbHelper.markTaskCompleted(task.getId());
        loadTasks();
    }
}
