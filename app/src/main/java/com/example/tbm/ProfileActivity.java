package com.example.tbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    private ImageButton HomeBtn;
    private ImageButton LibraryBtn;
    private ImageButton AccountBtn;

    ImageButton changePass, message, feedback, logout;
    Database db;
    String curUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        db = Database.getInstance(this);

        if(getIntent() != null && getIntent().hasExtra("currentUser"))
            curUser = getIntent().getStringExtra("currentUser");

        connectView();
    }

    private void connectView(){
        // Account login or edit
        AccountBtn = (ImageButton)findViewById(R.id.accountBtn);
        if (curUser.equals("guest")) {
            AccountBtn.setImageResource(R.drawable.login_icon);
            AccountBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProfileActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
            });
        }
        else{
            AccountBtn.setImageResource(R.drawable.edit_icon);
            AccountBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ProfileActivity.this, "Edit account information", Toast.LENGTH_LONG).show();
                }
            });
        }

        // back to Home Screen
        HomeBtn = (ImageButton) findViewById(R.id.homeBtn);
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomepageActivity.class);
                intent.putExtra("currentUser", curUser);
                startActivity(intent);
            }
        });

        // Library Screen
        LibraryBtn = (ImageButton) findViewById(R.id.libraryBtn);
        LibraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, LibraryActivity.class));
            }
        });

        // Change Pasword
        changePass = (ImageButton) findViewById(R.id.ChangePassBtn);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Change password", Toast.LENGTH_LONG).show();
            }
        });

        // See Messages
        message = (ImageButton) findViewById(R.id.ChangePassBtn);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "See Messages", Toast.LENGTH_LONG).show();
            }
        });

        // write feedback
        feedback = (ImageButton)findViewById(R.id.feedbackBtn);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Feedback", Toast.LENGTH_LONG).show();
            }
        });

        // log out
        logout = (ImageButton) findViewById(R.id.logoutBtn);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curUser = "guest";
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                intent.putExtra("currentUser", curUser);
                startActivity(intent);
            }
        });

    }
}