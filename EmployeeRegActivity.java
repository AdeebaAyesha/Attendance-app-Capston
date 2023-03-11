package com.example.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeeRegActivity extends AppCompatActivity {

    EditText id, userName, password, confirm;
    Button reg_btn;
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

        reg_btn = findViewById(R.id.reg_btn);
        account = findViewById(R.id.alreadyhaveaccount);
        userName=(EditText) findViewById(R.id.userName);
        password=(EditText) findViewById(R.id.password);
        confirm=(EditText) findViewById(R.id.confirm);
        id = findViewById(R.id.id);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Teacher");

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeRegActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(EmployeeRegActivity.this, "Successfully in next activity", Toast.LENGTH_SHORT).show();
            }
        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerForAuth();

            }
        });

    }

    private void PerForAuth() {
        String email = (String) userName.getText().toString();
        String pass = (String) password.getText().toString();
        String ids =(String) id.getText().toString();

        if (!email.matches(emailPattern)) {
            userName.setError("Enter Correct Email");
        } else if (pass.isEmpty() || pass.length() < 6) {
            password.setError("Enter Proper Password");
        }  else {
            progressDialog.setMessage("Wait....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful() && ids.equals(pass)) {
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
        String ida = (String) id.getText().toString();
        String use = (String) userName.getText().toString();
        String pass =(String) password.getText().toString();

        Users users = new Users(ida,use,pass);
        databaseReference.push().setValue(users);

        Intent intent = new Intent(EmployeeRegActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}