package com.example.todoproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {

    private EditText taskNameEditText, taskDescriptionEditText;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button updateButton;
    private SQLiteHelper dbHelper;
    private Task taskToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        dbHelper = new SQLiteHelper(this);

        taskNameEditText = findViewById(R.id.taskName);
        taskDescriptionEditText = findViewById(R.id.taskDescription);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        updateButton = findViewById(R.id.updateButton);

        // Get task ID from Intent
        int taskId = getIntent().getIntExtra("taskId", -1);

        // Load task details into fields
        taskToEdit = dbHelper.getTaskById(taskId);
        if (taskToEdit != null) {
            taskNameEditText.setText(taskToEdit.getName());
            taskDescriptionEditText.setText(taskToEdit.getDescription());
            datePicker.updateDate(taskToEdit.getYear(), taskToEdit.getMonth(), taskToEdit.getDay());
            timePicker.setHour(taskToEdit.getHour());
            timePicker.setMinute(taskToEdit.getMinute());
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTask();
            }
        });
    }

    private void updateTask() {
        String taskName = taskNameEditText.getText().toString().trim();
        String taskDescription = taskDescriptionEditText.getText().toString().trim();

        if (taskName.isEmpty()) {
            Toast.makeText(this, "Task name is mandatory", Toast.LENGTH_SHORT).show();
            return;
        }

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        // Update task in the object
        taskToEdit.setName(taskName);
        taskToEdit.setDescription(taskDescription);
        taskToEdit.setYear(year);
        taskToEdit.setMonth(month);
        taskToEdit.setDay(day);
        taskToEdit.setHour(hour);
        taskToEdit.setMinute(minute);

        // Call updateTask to save the changes in the database
        dbHelper.updateTask(taskToEdit);

        Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
        finish(); // Close the activity and return to the previous screen
    }
}
