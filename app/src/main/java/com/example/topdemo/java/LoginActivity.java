package com.example.topdemo.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topdemo.MainActivity;
import com.example.topdemo.R;

public class LoginActivity extends AppCompatActivity {


    TextView tvSignUp;
    public String username;
    public String password;
    EditText edName;
    EditText edPassword;
    Button btnLogin;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvSignUp = findViewById(R.id.tvSignUp);


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        databaseHelper = new DatabaseHelper(getApplicationContext());
        edName = findViewById(R.id.edName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = getTextFromEditText(edName);
                password = getTextFromEditText(edPassword);
                if (username.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    edName.setError("Please Enter valid email");
                } else {
                    doLogin();
                }
            }
        });
    }

    private void doLogin() {

        if (username.equals("") && password.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Insert Email and password", Toast.LENGTH_LONG).show();
        }
        if (username.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Insert Email ", Toast.LENGTH_LONG).show();
        }
        if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Insert Password ", Toast.LENGTH_LONG).show();
        } else {

            boolean status = (databaseHelper.checkUser(username, password));
            if (status) {
                Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, LoadDataActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "You are not Registerd!", Toast.LENGTH_LONG).show();

            }
        }

    }

    public String getTextFromEditText(EditText ed) {
        return ed.getText().toString().trim();
    }
}