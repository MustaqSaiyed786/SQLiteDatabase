package com.example.topdemo;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
    private DatabaseHelper databaseHelper;
    private User user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_sign_up, container, false);


        buttonSave = root.findViewById(R.id.btnSaveRecod);

        databaseHelper = new DatabaseHelper(requireContext());
        initObjects();

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
            et_first_name.setError(getString(R.string.entername));
        } else if (lastName.isEmpty()) {
            edtLastName.setError(getString(R.string.enterlastname));
        } else if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Please Enter valid email");
        } else if (mobiel.isEmpty()) {
            edtMoile.setError(getString(R.string.entermobile));
        } else if (mobiel.length() < 10) {
            edtMoile.setError(getString(R.string.invalideMobile));
        } else if (!rbMale.isChecked() && !rbFmale.isChecked()) {
            customToast("Please Select Gender");
        } else if (education.isEmpty()) {
            edtEducation.setError(getString(R.string.eduDetails));
        } else if (country.isEmpty()) {
            edtCountry.setError(getString(R.string.entercountry));
        } else if (password.isEmpty()) {
            edtPassword.setError(getString(R.string.enterpassword));
        } else {
            saveData();
        }

    }

    public void saveData() {

        user.setFirst_name(firstName.trim());
        user.setEmail(email.trim());
        user.setPassword(password.trim());
        user.setLast_name(lastName.trim());
        user.setMobile(mobiel.trim());
        user.setGende(gender);
        user.setEducation(education.trim());
        user.setCuntry(country.trim());
        if (databaseHelper.checkUser(email)) {
            customToast("User Exist In Database Please Login With Mail Id ");
        } else {
            databaseHelper.addUser(user);
            clearEditTetx(et_first_name);
            clearEditTetx(edtLastName);
            clearEditTetx(edtMoile);
            clearEditTetx(edtEmail);
            clearEditTetx(edtEducation);
            clearEditTetx(edtCountry);
            clearEditTetx(edtPassword);
            radioGroup.clearCheck();
            customToast("User Add");


        }

    }

    public void customToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }


    private void initObjects() {
        databaseHelper = new DatabaseHelper(getContext());
        user = new User();
    }

    public String getTextFromEditText(EditText ed) {
        return ed.getText().toString().trim();
    }

    private void clearEditTetx(EditText editText) {
        editText.setText("");
    }
}