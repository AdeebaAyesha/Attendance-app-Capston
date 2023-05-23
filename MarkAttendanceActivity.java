package com.example.report;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MarkAttendanceActivity extends AppCompatActivity {

    TextView location1;
    EditText department, subject;
    DatabaseReference databaseReference;
    Button mark_btn;
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        location1 = findViewById(R.id.location);
//        longitude = findViewById(R.id.longitude);
//        address = findViewById(R.id.address);
//        city = findViewById(R.id.city);
//        country = findViewById(R.id.country);
        mark_btn = findViewById(R.id.mark_btn);
        department = findViewById(R.id.department);
        subject = findViewById(R.id.subject);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Attendance");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        mark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    sendDataToDatabase();
            }
        });

        location1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLastLocation();
            }
        });
    }

    private void getLastLocation() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null){

                                Geocoder geocoder = new Geocoder(MarkAttendanceActivity.this, Locale.getDefault());
                                List<Address> addresses = null;

                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
                                    location1.setText(addresses.get(0).getAddressLine(0)+addresses.get(0).getLocality());

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    });
        }else {

            askPermission();

        }
    }

    private void sendDataToDatabase() {
        String dept = (String) department.getText().toString();
        String subj = (String) subject.getText().toString();
        String locate =(String) location1.getText().toString();

        Attendance attendance = new Attendance(dept, subj, locate);
        databaseReference.push().setValue(attendance);

        Intent intent = new Intent(MarkAttendanceActivity.this, EmployeeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void askPermission() {

        ActivityCompat.requestPermissions(MarkAttendanceActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                getLastLocation();

            }else {

                Toast.makeText(this, "Please provide the required permission", Toast.LENGTH_SHORT).show();

            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
