package com.example.topdemo;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class SignUpFragment extends Fragment {

    private String TAG = "SignUp";
    private AppCompatButton buttonSave;
    private EditText et_first_name;
    private EditText edtLastName;
    private EditText edtMoile;
    private EditText edtCountry;
    private EditText edtEmail;
    private EditText edtEducation;
    private EditText edtPassword;
    private String firstName;
    private String lastName;
    private String country;
    private String mobiel;
    private String email;
    private String education;
    private String password;
    private String gender;
    RadioGroup radioGroup;
    RadioButton rbMale, rbFmale;

    String receieveOk;
    LoginDatabaseAdapter loginDataBaseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*getActivity().getSupportFragmentManager().popBackStack(LoginFragment.class.getSimpleName(), 0);*/
        final View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        loginDataBaseAdapter = new LoginDatabaseAdapter(getActivity());
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        buttonSave = root.findViewById(R.id.btnSaveRecod);

        et_first_name = root.findViewById(R.id.edName);
        edtLastName = root.findViewById(R.id.edLastname);
        edtCountry = root.findViewById(R.id.edCountry);
        edtMoile = root.findViewById(R.id.edMobile);
        edtEducation = root.findViewById(R.id.edEducation);
        education = edtEducation.getText().toString();
        edtEmail = root.findViewById(R.id.edEmail);
        edtPassword = root.findViewById(R.id.edPassword);

        radioGroup = root.findViewById(R.id.radioGroup);
        rbMale = (RadioButton) root.findViewById(R.id.rbMale);
        rbFmale = (RadioButton) root.findViewById(R.id.rbFmale);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbMale:
                        Log.e(TAG, "onCheckedChanged: " + gender);
                        gender = rbMale.getText().toString();
                        break;
                    case R.id.rbFmale:
                        gender = rbFmale.getText().toString();
                        break;
                }
            }
        });


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = getTextFromEditText(et_first_name);
                lastName = getTextFromEditText(edtLastName);
                country = getTextFromEditText(edtCountry);
                mobiel = getTextFromEditText(edtMoile);
                email = getTextFromEditText(edtEmail);
                password = getTextFromEditText(edtPassword);
                education = getTextFromEditText(edtEducation);

                chekValidation();

            }
        });


        return root;

    }

    private void chekValidation() {
        if (firstName.equals("")) {
            customToast("Please Enter Name");
        } else if (lastName.isEmpty()) {
            customToast("Please Enter Last Name");
        } else if (email.isEmpty()) {
            customToast("Please Enter Email");
        } else if (mobiel.isEmpty()) {
            customToast("Please Enter Mobile");
        } else if (!rbMale.isChecked() && !rbFmale.isChecked()) {
            customToast("Please Select Gender");
        } else if (education.isEmpty()) {
            customToast("Please Enter Education Details ");
        } else if (country.isEmpty()) {
            customToast("Please Enter Country");
        } else if (password.isEmpty()) {
            customToast("Please Enter Password");
        } else {
            saveData();
        }

    }

    public void saveData() {
        Log.e(TAG, "saveData: " + gender);
        receieveOk = loginDataBaseAdapter.insertEntry(firstName, lastName, email, mobiel, gender, education, country, password);
    }

    public void customToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    public String getTextFromEditText(EditText ed) {
        return ed.getText().toString().trim();
    }
}