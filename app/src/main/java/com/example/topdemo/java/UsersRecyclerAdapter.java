package com.example.topdemo.java;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.topdemo.R;
import com.example.topdemo.java.User;
import com.example.topdemo.java.onDrawerClick;

import java.util.List;


public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<User> listUsers;
    private onDrawerClick onDrawerClick;


    public UsersRecyclerAdapter(List<User> listUsers, onDrawerClick onDrawerClick) {
        this.listUsers = listUsers;
        this.onDrawerClick = onDrawerClick;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details_user, parent, false);*/
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview, parent, false);

        return new UserViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewFullName.setText(listUsers.get(position).getFirst_name() + listUsers.get(position).getLast_name());
       /* holder.textViewFullName.setText(listUsers.get(position).getFirst_name() + listUsers.get(position).getLast_name());
        holder.textViewMobile.setText(listUsers.get(position).getMobile());
        holder.textViewEmail.setText(listUsers.get(position).getEmail());
        holder.textViewEducation.setText(listUsers.get(position).getEducation());
        holder.textViewGender.setText(listUsers.get(position).getGende());
        holder.textViewCountry.setText(listUsers.get(position).getCuntry());*/
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(), "" + listUsers.size());
        return listUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewFullName, textViewEmail, textViewMobile, textViewGender, textViewCountry, textViewEducation;

        public UserViewHolder(View view) {
            super(view);
            textViewFullName = view.findViewById(R.id.tvName);
           /* textViewFullName = view.findViewById(R.id.tvFullname);
            textViewEmail = view.findViewById(R.id.tvEmail);
            textViewMobile = view.findViewById(R.id.tvMobile);
            textViewGender = view.findViewById(R.id.tvGender);
            textViewEducation = view.findViewById(R.id.tvEducation);
            textViewCountry = view.findViewById(R.id.tvCountry);*/
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDrawerClick.onClickUser(listUsers.get(getPosition()));
                }
            });

        }

    }


}
