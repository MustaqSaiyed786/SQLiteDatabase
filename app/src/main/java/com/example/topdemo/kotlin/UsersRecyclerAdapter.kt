package com.example.topdemo.kotlin

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.topdemo.R
import com.example.topdemo.java.User
import com.example.topdemo.java.onDrawerClick

class UsersRecyclerAdapter(private val listUsers: List<User>, private val onDrawerClick: onDrawerClick) : RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // inflating recycler item view
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.itemview, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.textViewName.text = listUsers[position].first_name
    }

    override fun getItemCount(): Int {
        Log.v(UsersRecyclerAdapter::class.java.simpleName, "" + listUsers.size)
        return listUsers.size
    }

    /**
     * ViewHolder class
     */
    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewName: TextView

        init {
            textViewName = view.findViewById(R.id.tvName)
            view.setOnClickListener { onDrawerClick.onClickUser(listUsers[position].email) }
        }
    }

}