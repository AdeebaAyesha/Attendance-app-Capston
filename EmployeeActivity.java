package com.example.report;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmployeeActivity extends AppCompatActivity {

    TextView names, profile, report, markAttendance, viewAttendance;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    private final static int REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        getSupportActionBar().hide();
        names = findViewById(R.id.names);
        profile = findViewById(R.id.profile);
        report = findViewById(R.id.report);
        markAttendance = findViewById(R.id.markAttendance);
        viewAttendance = findViewById(R.id.viewAttendance);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        Intent intent = getIntent();
        String txt = intent.getStringExtra("Word");
        names.setText(txt);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
                profileIntent.putExtra("email", mUser.getEmail());
                startActivity(profileIntent);
            }
        });

        markAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent intent = new Intent(EmployeeActivity.this, MarkAttendanceActivity.class);
                startActivity(intent);
//                getLastLocation();
//
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



//    private void getLastLocation() {
//             askPermission();
//
//            }

//    private void getLastLocation() {
//
//        ActivityCompat.requestPermissions(EmployeeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode == REQUEST_CODE){
//
//            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                Intent intent = new Intent(EmployeeActivity.this, MarkAttendanceActivity.class);
//                startActivity(intent);
//
//            }else {
//
//                Toast.makeText(this, "Please provide the required permission", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
}