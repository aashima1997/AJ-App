package com.example.ajapp.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.ajapp.Room.Attendees
import com.example.ajapp.Login.viewModel.LoginViewModel
import com.example.ajapp.Menu.ui.Activity.NavDrawer
import com.example.ajapp.R
import com.example.ajapp.Login.ui.view.Activity.Register
import com.example.ajapp.Login.ui.passwordForget
import java.util.*

class Login : AppCompatActivity() {
    lateinit var mUserViewModel: LoginViewModel


    val namelogin by lazy {findViewById<EditText>(R.id.namelogin)}
    val passlogin by lazy {findViewById<EditText>(R.id.passlogin)}
    val loginb1 by lazy {findViewById<Button>(R.id.login)}
    val notyet by lazy {findViewById<TextView>(R.id.nt)}
    val forgetpassword by lazy {findViewById<TextView>(R.id.frpass)}





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //creating an instance for the view model as we had passed parameters in the view model class
        mUserViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(
                LoginViewModel::class.java);
        val present=findViewById<RadioButton>(R.id.rad1)
        val date=findViewById<TextView>(R.id.t1)
        present.visibility= View.GONE
        date.visibility= View.GONE

        loginb1.setOnClickListener{
try {


    val username1 = namelogin.text.toString()
    val password1 = passlogin.text.toString()

    //getting all users
    val valid = mUserViewModel.getUsername(username1)

    val present1 = present.text.toString()
    val user1 = Attendees(0, username1, present1, Calendar.getInstance().time)
    mUserViewModel.addUser1(user1)

    //validating the given passsword with all users
    if (password1 == valid.Password) {
        Toast.makeText(this, "successfully logged in", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, NavDrawer::class.java))
    } else {
        Toast.makeText(this, "Incorrect Username/Password", Toast.LENGTH_LONG).show()
    }

}catch (e:java.lang.Exception){
    Toast.makeText(this, "Inputs required", Toast.LENGTH_SHORT).show()
}
        }
        notyet.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
forgetpassword.setOnClickListener {
    val intent=Intent(this, passwordForget::class.java)
    startActivity(intent)
}


    }
}