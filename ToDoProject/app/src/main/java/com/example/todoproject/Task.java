package com.example.todoproject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Task {
    private int id;               // Task ID
    private String name;          // Task Name
    private String description;   // Task Description
    private int year;             // Task Deadline Year
    private int month;            // Task Deadline Month
    private int day;              // Task Deadline Day
    private int hour;             // Task Deadline Hour
    private int minute;           // Task Deadline Minute
    private boolean isCompleted;  // Task completion status

    // Constructor that accepts all parameters
    public Task(int id, String name, String description, int year, int month, int day, int hour, int minute, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.isCompleted = isCompleted;
    }

    // Constructor without the id (used when creating a new task)
    public Task(String name, String description, int year, int month, int day, int hour, int minute, boolean isCompleted) {
        this.id = 0;  // id is set to 0, to be auto-generated by SQLite
        this.name = name;
        this.description = description;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.isCompleted = isCompleted;
    }

    // Getter and Setter for ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for Task Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for Task Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for Task Year
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Getter and Setter for Task Month
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    // Getter and Setter for Task Day
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    // Getter and Setter for Task Hour
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    // Getter and Setter for Task Minute
    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    // Getter and Setter for Task Completion status
    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Method to get formatted deadline
    public String getFormattedDeadline() {
        // Create a Calendar object using the task's year, month, day, hour, and minute
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, 0); // Month is 0-indexed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }
}
