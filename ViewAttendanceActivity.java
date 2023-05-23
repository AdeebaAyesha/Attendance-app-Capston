package com.example.report;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewAttendanceActivity extends AppCompatActivity {

    AdapterAttendance myAdapter;
    ArrayList<Attendance> list;
    RecyclerView myrecyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        getSupportActionBar().hide();

        myrecyclerView = findViewById(R.id. myrecyclerView);
        myrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Attendance> options =
                new FirebaseRecyclerOptions.Builder<Attendance>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Attendance"),Attendance.class)
                        .build();

        myAdapter = new AdapterAttendance(options);
        myrecyclerView.setAdapter(myAdapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}