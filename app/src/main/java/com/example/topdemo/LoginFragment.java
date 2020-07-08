package com.example.topdemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {


    public String username;
    public String password;
    String storedPassword;

    EditText edName;
    EditText edPassword;
    AppCompatButton btnLogin;
    Cursor cursor;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        databaseHelper = new DatabaseHelper(requireContext());
        edName = view.findViewById(R.id.edLUserName);
        edPassword = view.findViewById(R.id.edLPassword);
        btnLogin = view.findViewById(R.id.btndoLogin);
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
        return view;
    }

    private void doLogin() {

        if (username.equals("") && password.equals("")) {
            Toast.makeText(getContext(), "Please Insert Email and password", Toast.LENGTH_LONG).show();
        }
        if (username.isEmpty()) {
            Toast.makeText(getContext(), "Please Insert Email ", Toast.LENGTH_LONG).show();
        }
        if (password.isEmpty()) {
            Toast.makeText(getContext(), "Please Insert Password ", Toast.LENGTH_LONG).show();
        } else {

            boolean status = (databaseHelper.checkUser(username, password));
            if (status) {
                Toast.makeText(getContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getContext(), "You are not Registerd!", Toast.LENGTH_LONG).show();

            }
        }

    }

    public String getTextFromEditText(EditText ed) {
        return ed.getText().toString().trim();
    }


}