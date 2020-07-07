package com.example.topdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoginFragment extends Fragment {


    public String username;
    private String password;
    String storedPassword;

    LoginDatabaseAdapter loginDataBaseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginDataBaseAdapter = new LoginDatabaseAdapter(getActivity());


        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}