package com.example.report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    String email;
    String USERid = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child("Users").child("Teacher");

        EditText updateNames = findViewById(R.id.updateNames);
        EditText updateUserName = findViewById(R.id.updateUserName);
        EditText updatePassword = findViewById(R.id.updatePassword);
        EditText updateId = findViewById(R.id.updateId);
        EditText updatePhone = findViewById(R.id.updatePhone);
//
//        mAuth = FirebaseAuth.getInstance();
//        mUser = mAuth.getCurrentUser();
        FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();

        userRef.addValueEventListener(new ValueEventListener() {
            String fname, fid, fpass, fphone;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(email)) {
                        fid  = keyId.child("id").getValue(String.class);
                        fname = keyId.child("name").getValue(String.class);
                        fpass = keyId.child("password").getValue(String.class);
                        fphone = keyId.child("phone_number").getValue(String.class);
                    }

                }
                updateUserName.setText(email);
                updateNames.setText(fname);
                updatePassword.setText(fpass);
                updateId.setText(fid);
                updatePhone.setText(fphone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Button update_butn = findViewById(R.id.update_butn);
//        update_butn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("name",updateNames.getText().toString());
//                map.put("email",updateUserName.getText().toString());
//                map.put("password",updatePassword.getText().toString());
//                map.put("id",updateId.getText().toString());
//                map.put("phone_number",updatePhone.getText().toString());
//
//                        USERid = muser.getUid();
//                FirebaseDatabase.getInstance().getReference().child("Users").child("Teacher")
//                        .child(USERid)
//                        .updateChildren(map)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                Toast.makeText(UpdateActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent1 = new Intent(UpdateActivity.this, ProfileActivity.class);
//                                startActivity(intent1);
//                            }
//                        });
//            }
//        });

    }
}