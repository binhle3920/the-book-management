package com.example.tbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SearchResultActivity extends AppCompatActivity {
    private ImageButton HomeBtn;
    private ImageButton LibraryBtn;
    private ImageButton ProfileBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        connectView();
    }

    private void connectView(){
        HomeBtn = (ImageButton) findViewById(R.id.homeBtn);
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, HomepageActivity.class));
            }
        });

        LibraryBtn = (ImageButton) findViewById(R.id.libraryBtn);
        LibraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, LibraryActivity.class));
            }
        });

        ProfileBtn = (ImageButton) findViewById(R.id.profileBtn);
        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchResultActivity.this, ProfileActivity.class));
            }
        });
    }
}