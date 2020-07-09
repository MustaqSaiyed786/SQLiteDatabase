package com.example.topdemo.kotlin

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.topdemo.R

class LoginActivity : AppCompatActivity() {

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
        setContentView(R.layout.activity_login)
        
    }


}