package com.example.todoproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskHistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_history);

        dbHelper = new SQLiteHelper(this);
        recyclerView = findViewById(R.id.recyclerView);  // Corrected to match the ID in your XML
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch completed tasks and set them in the adapter
        List<Task> completedTasks = dbHelper.getCompletedTasks();
        adapter = new TaskAdapter(completedTasks, null, true);  // Pass true to indicate it's history page
        recyclerView.setAdapter(adapter);
    }
}
