package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EmployeeActivity extends AppCompatActivity {

    TextView profile, report, markAttendance, viewAttendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        profile = findViewById(R.id.profile);
        report = findViewById(R.id.report);
        markAttendance = findViewById(R.id.markAttendance);
        viewAttendance = findViewById(R.id.viewAttendance);

        markAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeActivity.this, MarkAttendanceActivity.class);
                startActivity(intent);
            }
        });

        viewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeActivity.this, ViewAttendanceActivity.class);
                startActivity(intent);
            }
        });

    }
}