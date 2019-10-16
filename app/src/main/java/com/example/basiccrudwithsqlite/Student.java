package com.example.basiccrudwithsqlite;

public class Student {
    private int studentId;
    private String studentName;

    public Student() {}

    public Student(int id, String name) {
        this.studentId = id;
        this.studentName = name;
    }

    public void setStudentId(int id) {
        this.studentId = id;
    }

    public void setStudentName(String name) {
        this.studentName = name;
    }

    public int getStudentId() {
        return this.studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }
}
