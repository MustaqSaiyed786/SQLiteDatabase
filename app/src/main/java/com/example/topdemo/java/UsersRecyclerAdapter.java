package com.example.topdemo.java;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.topdemo.R;

import java.util.List;


public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<User> listUsers;
    private com.example.topdemo.java.onDrawerClick onDrawerClick;

    public UsersRecyclerAdapter(List<User> listUsers, com.example.topdemo.java.onDrawerClick onDrawerClick) {
        this.listUsers = listUsers;
        this.onDrawerClick = onDrawerClick;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewName.setText(listUsers.get(position).getFirst_name());
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public UserViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.tvName);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDrawerClick.onClickUser(listUsers.get(getPosition()).getEmail());
                }
            });
        }

    }


}
