package com.example.mycampus_assistant;

public class AssignmentExamItem {
    private String title;
    private String dueDate;
    private boolean isAlert;

    public AssignmentExamItem(String title, String dueDate, boolean isAlert) {
        this.title = title;
        this.dueDate = dueDate;
        this.isAlert = isAlert;
    }

    public String getTitle() { return title; }
    public String getDueDate() { return dueDate; }
    public boolean isAlert() { return isAlert; }
}
