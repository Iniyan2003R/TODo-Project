package com.example.todoproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo_db";

    // Table name and columns
    private static final String TABLE_TASKS = "tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_YEAR = "year";
    private static final String KEY_MONTH = "month";
    private static final String KEY_DAY = "day";
    private static final String KEY_HOUR = "hour";
    private static final String KEY_MINUTE = "minute";
    private static final String KEY_COMPLETED = "completed";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_YEAR + " INTEGER,"
                + KEY_MONTH + " INTEGER,"
                + KEY_DAY + " INTEGER,"
                + KEY_HOUR + " INTEGER,"
                + KEY_MINUTE + " INTEGER,"
                + KEY_COMPLETED + " INTEGER" + ")";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    // Add a new task to the database
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_YEAR, task.getYear());
        values.put(KEY_MONTH, task.getMonth());
        values.put(KEY_DAY, task.getDay());
        values.put(KEY_HOUR, task.getHour());
        values.put(KEY_MINUTE, task.getMinute());
        values.put(KEY_COMPLETED, task.isCompleted() ? 1 : 0);

        // Insert the new task and get the task ID
        long id = db.insert(TABLE_TASKS, null, values);
        task.setId((int) id); // Setting the ID of the task (using autoincrement ID)
        db.close();
    }

    // Get all tasks from the database
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                        cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndex(KEY_YEAR)),
                        cursor.getInt(cursor.getColumnIndex(KEY_MONTH)),
                        cursor.getInt(cursor.getColumnIndex(KEY_DAY)),
                        cursor.getInt(cursor.getColumnIndex(KEY_HOUR)),
                        cursor.getInt(cursor.getColumnIndex(KEY_MINUTE)),
                        cursor.getInt(cursor.getColumnIndex(KEY_COMPLETED)) == 1
                );
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return taskList;
    }

    // Get all completed tasks
    public List<Task> getCompletedTasks() {
        List<Task> completedTaskList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TASKS + " WHERE " + KEY_COMPLETED + " = 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                        cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndex(KEY_YEAR)),
                        cursor.getInt(cursor.getColumnIndex(KEY_MONTH)),
                        cursor.getInt(cursor.getColumnIndex(KEY_DAY)),
                        cursor.getInt(cursor.getColumnIndex(KEY_HOUR)),
                        cursor.getInt(cursor.getColumnIndex(KEY_MINUTE)),
                        cursor.getInt(cursor.getColumnIndex(KEY_COMPLETED)) == 1
                );
                completedTaskList.add(task);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return completedTaskList;
    }

    // Update task completion status
    public void updateTaskCompletion(int taskId, boolean isCompleted) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COMPLETED, isCompleted ? 1 : 0);
        db.update(TABLE_TASKS, values, KEY_ID + " = ?", new String[]{String.valueOf(taskId)});
    }

    // Delete a task
    public void deleteTask(int taskId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + " = ?", new String[]{String.valueOf(taskId)});
        db.close();
    }
}
