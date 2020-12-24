package com.example.tbm;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        TextView btn = (TextView) findViewById(R.id.toSignInButton);
        btn.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}