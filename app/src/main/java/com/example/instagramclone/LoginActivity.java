package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser()!=null){
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Signup Button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                SignUpUser(username,password);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Onclick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                loginUser(username, password);
                
            }
        });
    }

    private void SignUpUser(String username, String password) {

        ParseUser user = new ParseUser();

        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with SignUp!", e);
                    return;
                } else {
                    goMainActivity();
                    Toast.makeText(LoginActivity.this, "Successful sign up", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loginUser(String username, String password) {

        Log.i(TAG, "Attempting to login user: "+ username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null){
                   Log.e(TAG, "Issue with login", e);
                    Toast.makeText(LoginActivity.this, "Issue with login",Toast.LENGTH_SHORT).show();
                   return;

                }
                
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Successful Login!",Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}