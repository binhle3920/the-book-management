package com.example.tbm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    DatabaseHelper db;
    HashHelper md5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        //init
        initialize();

        TextView suBtn = (TextView) findViewById(R.id.toRegisButton);
        suBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        Button siBtn = (Button) findViewById(R.id.signInButton);
        siBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (validateInput()) {
                    if (!(db.checkUser(username.getText().toString()))) {
                        String pass = db.getPassword(username.getText().toString());
                        String userPass = md5.getMd5(password.getText().toString());

                        if (pass.equals(userPass)) {
                            Toast.makeText(getApplicationContext(), "Welcome " + username.getText().toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Your username or password is not correct", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }


    private void initialize() {
        db = new DatabaseHelper(this);
        md5 = new HashHelper();

        username = (EditText) findViewById(R.id.inputUsernameSI);
        password = (EditText) findViewById(R.id.inputPasswordSI);
    }

    private boolean validateInput() {
        //if empty
        if (username.getText().toString().equals("")) {
            username.setError("Please enter username");
            return false;
        }
        if (password.getText().toString().equals("")) {
            password.setError("Please enter password");
            return false;
        }

        //length
        if (username.getText().toString().length() < 3) {
            username.setError("Your username is too short");
            return false;
        }
        if (username.getText().toString().length() >= 10) {
            username.setError("Your username is too long");
            return false;
        }
        if (password.getText().toString().length() < 3) {
            password.setError("Your password is too short");
            return false;
        }
        if (password.getText().toString().length() > 30) {
            password.setError("Your password is too long");
            return false;
        }
        return true;
    }
}