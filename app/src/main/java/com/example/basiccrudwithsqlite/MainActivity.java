package com.example.basiccrudwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView lst;
    EditText studentId;
    EditText studentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lst = (TextView) findViewById(R.id.lst);
        studentId = (EditText) findViewById(R.id.studentIdInput);
        studentName = (EditText) findViewById(R.id.studentNameInput);
    }

    public void loadStudents(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Log.e("Response: ", dbHandler.loadHandler().toString());
        lst.setText(dbHandler.loadHandler().toString());
        studentId.setText("");
        studentName.setText("");
    }

    public void addStudent(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        int id = Integer.parseInt(studentId.getText().toString());
        String name = studentName.getText().toString();
        Student student = new Student(id, name);
        dbHandler.addHandler(student);
        studentId.setText("");
        studentName.setText("");
    }

    public void findStudent(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Student student = dbHandler.findHandler(studentName.getText().toString());
        if (student != null) {
            lst.setText(String.valueOf(student.getStudentId()) + " " + student.getStudentName() + System.getProperty("line.separator"));
            studentId.setText("");
            studentName.setText("");
        } else {
            lst.setText("No Match Found");
            studentId.setText("");
            studentName.setText("");
        }
    }

    public void removeStudent(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,
                null, 1);
        boolean result = dbHandler.deleteHandler(Integer.parseInt(studentId.getText().toString()));
        if (result) {
            studentId.setText("");
            studentName.setText("");
            lst.setText("Record Deleted");
        } else
            lst.setText("No Match Found");
    }

    public void updateStudent(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,null, 1);
        boolean result = dbHandler.updateHandler(Integer.parseInt(studentId.getText().toString()), studentName.getText().toString());
        if (result) {
            studentId.setText("");
            studentName.setText("");
            lst.setText("Record Updated");
        } else
            lst.setText("No Match Found");
    }
}
