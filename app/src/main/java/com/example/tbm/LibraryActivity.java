package com.example.tbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LibraryActivity extends AppCompatActivity {
    private ImageButton HomeBtn;
    private ImageButton LibraryBtn;
    private ImageButton ProfileBtn;
    Database db;
    String curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);

        db = Database.getInstance(this);

        if(getIntent() != null && getIntent().hasExtra("currentUser"))
            curUser = getIntent().getStringExtra("currentUser");
        connectView();
    }

    private void connectView(){
        HomeBtn = (ImageButton) findViewById(R.id.homeBtn);
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibraryActivity.this, HomepageActivity.class);
                intent.putExtra("currentUser", curUser);
                startActivity(intent);
            }
        });

        ProfileBtn = (ImageButton) findViewById(R.id.profileBtn);
        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibraryActivity.this, ProfileActivity.class);
                intent.putExtra("currentUser", curUser);
                startActivity(intent);
            }
        });
    }
}