package com.example.ajapp.Login.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.ajapp.Login.viewModel.LoginViewModel
import com.example.ajapp.R

class passwordForget : AppCompatActivity() {
    lateinit var mUserViewModel: LoginViewModel
    val passForgetName by lazy { findViewById<EditText>(R.id.passforgetName) }
    val passForgetBtn by lazy { findViewById<Button>(R.id.passforgetButton) }
    val userPassword by lazy { findViewById<TextView>(R.id.passwordIs) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_forget)
        mUserViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
            ).get(
                LoginViewModel::class.java
            );
        passForgetBtn.setOnClickListener {
            if (passForgetName.text.toString() == "") {
                Toast.makeText(this, "Username is Mandatory", Toast.LENGTH_LONG).show()
            } else {
                val username1 = passForgetName.text.toString()
                val valid1 = mUserViewModel.getUsername(username1)
                userPassword.text = valid1.Password
            }
        }
    }
}