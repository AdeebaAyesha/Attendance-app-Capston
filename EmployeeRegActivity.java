package com.example.report;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeeRegActivity extends AppCompatActivity {

    EditText ids, userName, passwords, confirm, names, phone;
    Button reg_btn;
    RadioGroup gender;
    RadioButton male, female;
    TextView account;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_reg);

        getSupportActionBar().hide();

        reg_btn = findViewById(R.id.reg_btn);
        account = findViewById(R.id.alreadyhaveaccount);
        userName = (EditText) findViewById(R.id.userName);
        passwords = (EditText) findViewById(R.id.password);
        names = findViewById(R.id.names);
 //       confirm = (EditText) findViewById(R.id.confirm);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        ids = findViewById(R.id.id);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Teacher");
//        account.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(EmployeeRegActivity.this, LoginActivity.class);
//                startActivity(intent);
//                Toast.makeText(EmployeeRegActivity.this, "Successfully in next activity", Toast.LENGTH_SHORT).show();
//            }
//        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerForAuth();

            }
        });
    }

    private void PerForAuth() {
        String email = (String) userName.getText().toString();
        String pass = (String) passwords.getText().toString();
        String id =(String) ids.getText().toString();
        String nam = names.getText().toString();
        String phon = phone.getText().toString();

        if (!email.matches(emailPattern)) {

            userName.setError("Enter Correct Email");

        } else if (pass.isEmpty() || pass.length() < 6 ) {

            passwords.setError("Enter Proper Password");

        } else if (nam.isEmpty() && phon.isEmpty() ) {

            names.setError("Enter your Name");
            phone.setError("Must enter you phone no.");

        }
//        else if (female.isChecked() || male.isChecked()) {
//
//            female.setError("Enter your gender");
//        }
        else {
            progressDialog.setMessage("Wait....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful() && id.equals(pass)) {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(EmployeeRegActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(EmployeeRegActivity.this, "Already Exists email" + task.getException(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        String id = (String) ids.getText().toString();
        String name = (String) names.getText().toString();
        String email =(String) userName.getText().toString();
        String password =(String) passwords.getText().toString();
        String phone_number =(String) phone.getText().toString();
        String ml = male.getText().toString();
        String fl = female.getText().toString();

        Users users = new Users(id, name, email, password, phone_number);
        databaseReference.push().setValue(users);

        Intent intent = new Intent(EmployeeRegActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}