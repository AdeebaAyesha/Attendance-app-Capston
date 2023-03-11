package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attendance.Classes.Attendance;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MarkAttendanceActivity extends AppCompatActivity {

    EditText department, subject, location;
    Button mark_btn;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        department = findViewById(R.id.department);
        subject = findViewById(R.id.subject);
        location = findViewById(R.id.location);
        mark_btn = findViewById(R.id.mark_btn);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Attendance");


        mark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String dept = department.getText().toString();
//                String sub = subject.getText().toString();
//                String loc = location.getText().toString();
//                Intent intent = new Intent(MarkAttendanceActivity.this, ViewAttendanceActivity.class);
//
//                Bundle bundle =new Bundle();
//
//                intent.putExtra("dept",dept);
//                intent.putExtra("sub",sub);
//                intent.putExtra("loc",loc);
//
//                intent.putExtras(bundle);
//                startActivity(intent);
//                Toast.makeText(MarkAttendanceActivity.this, "Data Sent!", Toast.LENGTH_SHORT).show();
                sendDataToDatabase();
            }
        });

    }

    private void sendDataToDatabase() {

        String dept = (String) department.getText().toString();
        String subj = (String) subject.getText().toString();
        String locate =(String) location .getText().toString();

        Attendance attendance = new Attendance(dept, subj, locate);
        databaseReference.push().setValue(attendance);

        Intent intent = new Intent(MarkAttendanceActivity.this, EmployeeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}