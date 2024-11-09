package com.example.todoproject;

public class Task {
    private int id;          // Task ID
    private String name;     // Task Name
    private String description; // Task Description
    private int year;        // Task Deadline Year
    private int month;       // Task Deadline Month
    private int day;         // Task Deadline Day
    private int hour;        // Task Deadline Hour
    private int minute;      // Task Deadline Minute
    private boolean isCompleted; // Task completion status

    // Constructor
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
}
