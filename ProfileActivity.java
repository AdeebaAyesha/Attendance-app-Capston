package com.example.report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private TextView name_TxtView, nameTxtView, workTxtView;
    ImageView edit;
    private TextView emailTxtView, phoneTxtView, videoTxtView, password_TxtView, twitterTxtView;
    private ImageView userImageView, emailImageView, phoneImageView, videoImageView,edit_btn;
    private ImageView facebookImageView, twitterImageView;
//    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Map<String, String> userMap;
    private String email;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    private String USERID = "";
//    private String userid;
//    private static final String USERS = "users";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //receive data from login screen
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child("Users").child("Teacher");
      //  Log.v("USERID", userRef.getKey());
        name_TxtView = findViewById(R.id.name_textview);
   //     nameTxtView = findViewById(R.id.name__textview);
    //    workTxtView = findViewById(R.id.workplace_textview);
        emailTxtView = findViewById(R.id.email_textview);
        phoneTxtView = findViewById(R.id.phone_textview);
      //  videoTxtView = findViewById(R.id.video_textview);
        password_TxtView = findViewById(R.id.password_textview);

        emailImageView = findViewById(R.id.email_imageview);
        phoneImageView = findViewById(R.id.phone_imageview);
        videoImageView = findViewById(R.id.phone_imageview);
        facebookImageView = findViewById(R.id.facebook_imageview);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        edit_btn = findViewById(R.id.edit_btn);
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                passUserData();
//            }
//        });

        // Read from the database
        userRef.addValueEventListener(new ValueEventListener() {
            String fname, mail, profession, workplace, phone;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot keyId : dataSnapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(email)) {
                        fname = keyId.child("id").getValue(String.class);
                        profession = keyId.child("name").getValue(String.class);
                        workplace = keyId.child("password").getValue(String.class);
                        phone = keyId.child("phone_number").getValue(String.class);
                    }
                }
//                nameTxtView.setText(fname);
                emailTxtView.setText(email);
                name_TxtView.setText(profession);
//                workTxtView.setText(workplace);
                phoneTxtView.setText(phone);
                password_TxtView.setText(workplace);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ProfileActivity.this, UpdateActivity.class);
                intent1.putExtra("email", mUser.getEmail());
                startActivity(intent1);
//                DialogPlus dialogPlus = DialogPlus.newDialog(emailTxtView.getContext()).
//                        setGravity(Gravity.CENTER)
//                        .setMargin(50, 0, 50, 0)
//                        .setContentHolder(new ViewHolder(R.layout.edit))
//                        .setExpanded(false)
//                        .create();
            }});
//                Users users = new Users();
//                updateNames.setText(users.getName());
//                updateUserName.setText(users.getEmail());
//                updatePassword.setText(users.getPassword());
//                updateId.setText(users.getId());
//                updatePhone.setText(users.getPhone_number());
//
//                Button update_butn = findViewById(R.id.update_butn);
//                update_butn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("name", updateNames.getText().toString());
//                        map.put("email", updateUserName.getText().toString());
//                        map.put("password", updatePassword.getText().toString());
//                        map.put("id", updateId.getText().toString());
//                        map.put("phone_number", updatePhone.getText().toString());
//
//                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                        USERID = user.getUid();
//                        FirebaseDatabase.getInstance().getReference().child("Users").child("Teacher")
//                                .child(USERID)
//                                .updateChildren(map)
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        dialogPlus.dismiss();
//                                    }
//                                });
//                    }
//                });
//                dialogPlus.show();
//            }
//        });
    }
//    private void passUserData() {
//        String userUsername = nameTxtView.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child("Teacher");
//
//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//
//                    String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
//                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
//                    String usernameFromDB = snapshot.child(userUsername).child("id").getValue(String.class);
//                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
//
//                    Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
//
//                    intent.putExtra("name", nameFromDB);
//                    intent.putExtra("email", emailFromDB);
//                    intent.putExtra("id", usernameFromDB);
//                    intent.putExtra("password", passwordFromDB);
//
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}