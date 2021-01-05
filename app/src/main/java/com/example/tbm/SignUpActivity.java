package com.example.tbm;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    private EditText username, email, password;
    Database db;
    HashHelper md5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //init
        initialize();

        //change layout
        TextView siBtn = (TextView) findViewById(R.id.toSignInButton);
        siBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LogInActivity.class);
                startActivity(intent);
            }
        });

        //user submit
        Button suBtn = (Button) findViewById(R.id.signInButton);
        suBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    boolean check = db.insertNewUser(username.getText().toString(), email.getText().toString(), md5.getMd5(password.getText().toString()));
                    if (check == true) {
                        Toast.makeText(getApplicationContext(), "You have signed up success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(v.getContext(), LogInActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Something error happen, please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initialize() {
        db = Database.getInstance(this);
        md5 = new HashHelper();

        username = (EditText) findViewById(R.id.inputUsername);
        email = (EditText) findViewById(R.id.inputEmail);
        password = (EditText) findViewById(R.id.inputPassword);
    }
    private boolean validateInput() {
        //if empty
        if (username.getText().toString().equals("")) {
            username.setError("Please enter username");
            return false;
        }
        if (email.getText().toString().equals("")) {
            email.setError("Please enter email");
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
        if (username.getText().toString().length() >= 15) {
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

        //valid account
        String account_name = username.getText().toString();
        if(account_name.equals("guest") || account_name.equals("admin")){
            username.setError("You don't have permission to sign up with this account");
            return false;
        }

        //valid email
        if (!(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())) {
            email.setError("Please enter a valid email address");
            return false;
        }

        //username and email exists
        if (!(db.checkEmail(email.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "Email already exists!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(db.checkUser(username.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "Username already exists!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}