package com.example.todoproject;

public class Task {
    private int id;
    private String name;
    private boolean isCompleted;

    public Task(int id, String name, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isCompleted() { return isCompleted; }
}
