package com.example.topdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SignUpFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*getActivity().getSupportFragmentManager().popBackStack(LoginFragment.class.getSimpleName(), 0);*/
        return inflater.inflate(R.layout.fragment_sign_up, container, false);

    }
}