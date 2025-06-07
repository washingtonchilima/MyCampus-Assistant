package com.example.mycampus_assistant;

public class ClassScheduleEntry {
    private String day;
    private String time;
    private String subject;

    public ClassScheduleEntry(String day, String time, String subject) {
        this.day = day;
        this.time = time;
        this.subject = subject;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }
}

