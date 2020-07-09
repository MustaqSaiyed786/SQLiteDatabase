package com.example.topdemo.java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topdemo.R;

import java.util.ArrayList;
import java.util.List;

public class LoadDataActivity extends AppCompatActivity implements onDrawerClick{


    public RecyclerView recycleUser;
    private List<User> listUsers;
    private DatabaseHelper databaseHelper;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private TextView fullname, education, email, mobile, country, gender;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_load_data);
        recycleUser = findViewById(R.id.recycleUser);

        fullname = findViewById(R.id.tvFullname);
        education = findViewById(R.id.tvEducation);
        email = findViewById(R.id.tvEmail);
        mobile = findViewById(R.id.tvMobile);
        country = findViewById(R.id.tvCountry);
        gender = findViewById(R.id.tvGender);

        listUsers = new ArrayList<>();
        usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycleUser.setLayoutManager(mLayoutManager);
        recycleUser.setItemAnimator(new DefaultItemAnimator());
        recycleUser.setHasFixedSize(true);
        recycleUser.setAdapter(usersRecyclerAdapter);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        listUsers.addAll(databaseHelper.getAllUser());
        usersRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickUser(User user) {
        fullname.setText(user.getFirst_name() + user.getLast_name());
        education.setText(user.getEducation());
        email.setText(user.getEmail());
        mobile.setText(user.getMobile());
        country.setText(user.getCuntry());
        gender.setText(user.getGende());
    }

}