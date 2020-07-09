package com.example.topdemo.java;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.topdemo.R;

public class SignUpActivity extends Activity {


    ConstraintLayout constraintLayoutToolbar;
    TextView tvTitle;
    ImageView imgBack;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        constraintLayoutToolbar = findViewById(R.id.signUpToolbar);
        tvTitle = constraintLayoutToolbar.findViewById(R.id.tvTitle);
        imgBack = constraintLayoutToolbar.findViewById(R.id.imgBack);
        tvTitle.setText("Sign Up");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        buttonSave = findViewById(R.id.btnSaveRecod);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        initObjects();

        et_first_name = findViewById(R.id.edName);
        edtLastName = findViewById(R.id.edLastname);
        edtCountry = findViewById(R.id.edCountry);
        edtMoile = findViewById(R.id.edMobile);
        edtEducation = findViewById(R.id.edEducation);
        education = edtEducation.getText().toString();
        edtEmail = findViewById(R.id.edEmail);
        edtPassword = findViewById(R.id.edPassword);

        radioGroup = findViewById(R.id.radioGroup);
        rbMale = findViewById(R.id.rbMale);
        rbFmale = findViewById(R.id.rbFmale);

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
            clearEditText(et_first_name);
            clearEditText(edtLastName);
            clearEditText(edtMoile);
            clearEditText(edtEmail);
            clearEditText(edtEducation);
            clearEditText(edtCountry);
            clearEditText(edtPassword);
            radioGroup.clearCheck();
            customToast("User Add");
            finish();
        }

    }

    public void customToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }


    private void initObjects() {
        databaseHelper = new DatabaseHelper(getApplicationContext());
        user = new User();
    }

    public String getTextFromEditText(EditText ed) {
        return ed.getText().toString().trim();
    }

    private void clearEditText(EditText editText) {
        editText.setText("");
    }
}
