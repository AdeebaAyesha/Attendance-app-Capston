package com.example.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.attendance.Classes.Attendance;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAttendanceActivity extends AppCompatActivity {

    ListView myListView;
    Button get_btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        myListView = findViewById(R.id.myListView);
        get_btn = findViewById(R.id.get_btn);

        get_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> a = new ArrayList<>();
                ArrayAdapter adapter = new ArrayAdapter(ViewAttendanceActivity.this, R.layout.list_item, a);
                myListView.setAdapter(adapter);
                FirebaseDatabase.getInstance().getReference().child("Attendance").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            a.clear();
                            for (DataSnapshot snapshot1:snapshot.getChildren())
                            {
                                Attendance attendance = snapshot1.getValue(Attendance.class);
                                String t = " "+attendance.getDept()+ " " +attendance.getSubj()+" "+attendance.getLocate();
                                a.add(t);

                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}