package com.example.sms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit1 = findViewById(R.id.username);
        edit2 = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        String username = edit1.getText().toString();
        String password = edit2.getText().toString();

        // TODO Add validation of the username and passwrowd

        if (username.equals("") || password.equals("")) {
            Toast.makeText(this, "Username or passrowd is empty", Toast.LENGTH_LONG).show();
        } else if (username.equals("Laila") && password.equals("1234")) {
            Toast.makeText(this, "Login was successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Username or passowrd is incorrect", Toast.LENGTH_LONG).show();
        }
    }



}