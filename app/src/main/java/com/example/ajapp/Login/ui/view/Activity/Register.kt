package com.example.ajapp.Login.ui.view.Activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ajapp.Login.viewModel.LoginViewModel
import com.example.ajapp.Login.Login
import com.example.ajapp.Menu.ui.Activity.NavDrawer
import com.example.ajapp.R
import com.example.ajapp.Room.User
import com.example.ajapp.SavedPreference
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.*
import java.util.*


@Suppress("DEPRECATION")
class Register : AppCompatActivity() {
    lateinit var mUserViewModel: LoginViewModel
    val loginBtn by lazy { findViewById<Button>(R.id.reg1) }

    val registerBtn by lazy { findViewById<Button>(R.id.reg) }
    val nameText by lazy { findViewById<EditText>(R.id.name) }
    val passText by lazy { findViewById<EditText>(R.id.ps) }
    val conpassText by lazy { findViewById<EditText>(R.id.cps) }
    val emailText by lazy { findViewById<EditText>(R.id.email) }
    val phoneText by lazy { findViewById<EditText>(R.id.ph) }
    val check by lazy {findViewById<CheckBox>(R.id.ck)}
    //google sign
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val Signin by lazy { findViewById<SignInButton>(R.id.signin) }
    val fblogin1 by lazy { findViewById<LoginButton>(R.id.fblogin) }
    val Tc1 by lazy { findViewById<TextView>(R.id.tc) }

    lateinit var alert:AlertDialog


    private val EMAIL: String? = "email"

    private var mAuth: FirebaseAuth? = null
    var callbackManager: CallbackManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        FacebookSdk.sdkInitialize(getApplicationContext());
        mUserViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(
            LoginViewModel::class.java
        )
        FirebaseApp.initializeApp(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1033843825929-r8iangqfa6pleg4epqu3sknpqb745a30.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        firebaseAuth = FirebaseAuth.getInstance()

        Signin.setOnClickListener { view: View? ->
            Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
            signInGoogle()
        }
        mAuth = FirebaseAuth.getInstance();
        fblogin1.setReadPermissions(Arrays.asList(EMAIL));
        callbackManager = CallbackManager.Factory.create();
        fblogin1.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    facebookHandlerCode(loginResult.accessToken)
                    startActivity(Intent(this@Register, NavDrawer::class.java))
                }

                override fun onCancel() {
                    // App code
                }

                override fun onError(exception: FacebookException) {
                    // App code
                }
            })
        loginBtn.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }
            Tc1.setMovementMethod(LinkMovementMethod.getInstance())

    }

    private fun facebookHandlerCode(token: AccessToken?) {
        val cred = FacebookAuthProvider.getCredential(token!!.getToken())
        mAuth!!.signInWithCredential(cred)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult?> {
                override fun onComplete(task: Task<AuthResult?>) {
                    if (task.isSuccessful) {
                        fblogin1.setVisibility(View.INVISIBLE)
                        startActivity(Intent(this@Register, NavDrawer::class.java))
                        finish()
                    }
                }
            })

    }


    private  fun signInGoogle(){
        val Req_Code:Int=123

        val signInIntent: Intent =mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,Req_Code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data);
        val Req_Code:Int=123

        if(requestCode==Req_Code){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account: GoogleSignInAccount? =completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException){
            Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount){
        val credential= GoogleAuthProvider.getCredential(account.idToken,null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {task->
            if(task.isSuccessful) {
                SavedPreference.setEmail(this, account.email.toString())
                SavedPreference.setUsername(this, account.displayName.toString())
                val intent = Intent(this, NavDrawer::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //google login user check
        if(GoogleSignIn.getLastSignedInAccount(this)!=null){
            startActivity(Intent(this, NavDrawer::class.java))
            finish()
            //facebook login use check
            val user = mAuth!!.currentUser
            if (user != null) {
                startActivity(Intent(this, NavDrawer::class.java))
                finish()
            }



        }

        var email1 = "[a-z0-9._-]+@[a-z]+.+[a-z]+"

        registerBtn.setOnClickListener() {
            //validating user
            if (nameText.text.toString() == "" || emailText.text.toString() == "" || passText.text.toString() == "" || conpassText.text.toString() == "" || phoneText.text.toString() == "") {
                Toast.makeText(
                    this,
                    "Inputs required",
                    Toast.LENGTH_LONG
                ).show()
            } else if (phoneText.text.toString().length != 10) {
                Toast.makeText(
                    this,
                    "enter a valid phone number",
                    Toast.LENGTH_LONG
                ).show()


            }
            else if (passText.text.toString().length < 5) {
                Toast.makeText(
                    this,
                    "password need minimum 5 characters",
                    Toast.LENGTH_LONG
                ).show()
            }
            else if (passText.text.toString() != conpassText.text.toString()) {
                Toast.makeText(
                    this,
                    "password Mismatched",
                    Toast.LENGTH_LONG
                ).show()

            }
            else if(check.isChecked==false)
            {
                Toast.makeText(
                    this,
                    "please Agree to Terms and Conditions",
                    Toast.LENGTH_LONG
                ).show()
            }
            else if(emailText.text.toString().trim().matches(email1.toRegex()) ) {
                insertDataToDatabase()

                val intent = Intent(this, Login::class.java)
                startActivity(intent)

            }

            else
                Toast.makeText(
                    this,
                    "Enter a valid Email",
                    Toast.LENGTH_LONG
                ).show()

        }
    }
    //inserting data in to the room db
    private fun insertDataToDatabase() {
        val username=nameText.text.toString()
        val password=passText.text.toString()
        val user= User(0,username,password)
        mUserViewModel.addUser(user)

    }

    fun clickHere(v: View) {

        tAndc()
    }

    private fun tAndc() {
        val isFirst:Boolean=getSharedPreferences("pref", MODE_PRIVATE).getBoolean("isFirst",true)
        if(isFirst){
            AlertDialog.Builder(this)
                .setTitle("Hello")
                .setMessage("jbyjvyufgvyubun67gumunio")
                .setNegativeButton("Decline",object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                        finish()
                    }

                })
                .setPositiveButton("Accept",object:DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        getSharedPreferences("pref", MODE_PRIVATE)
                            .edit()
                            .putBoolean("isFirst",false)
                            .apply()

                    }

                }).show()

        }
    }
}