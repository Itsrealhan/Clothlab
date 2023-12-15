package com.example.clothlab.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.clothlab.MainActivity
import com.example.clothlab.R
import com.google.android.material.textfield.TextInputLayout

class SigninActivity : AppCompatActivity() {

//  initiate every variable in this activity
    private lateinit var etEmailLayout: TextInputLayout
    private lateinit var etPasswordLayout: TextInputLayout
    private lateinit var btnSignIn: Button
    private lateinit var btnGoogleSignIn: LinearLayout
    private lateinit var linkForgotPassword: TextView
    private lateinit var linkSignUp: TextView

//  create life cycle for this activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

//      declare every variable
        etEmailLayout = findViewById(R.id.etEmailLayout)
        etPasswordLayout = findViewById(R.id.etPasswordLayout)
        btnSignIn = findViewById(R.id.btnSignIn)
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn)    // not used yet
        linkForgotPassword = findViewById(R.id.linkForgotPassword)  // not used yet
        linkSignUp = findViewById(R.id.linkSignUp)

//      get text from edit text in text input layout and return it in string form into variable
        val email = etEmailLayout.editText?.text
        val password = etPasswordLayout.editText?.text

//      function for email validation
        fun validEmail(): Pair<String?, Drawable?> {
//          default background value
            val background = resources.getDrawable(R.drawable.bg_et, this.theme)

//          execute if value of email variable is empty
            if (email.toString().isEmpty()) {
                Log.d("message1", "Must be filled")     // for checking purposes
                val message = "Must be filled"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

//          execute if value of email variable doesn't match the email patterns
            else if (!Patterns.EMAIL_ADDRESS.matcher(email.toString().trim()).matches()) {
                Log.d("message1", "Invalid email address")     // for checking purposes
                val message = "Invalid email address"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

            return Pair(null, background)
        }

//      function for password validation
        fun validPassword(): Pair<String?, Drawable?> {
//          default background value
            val background = resources.getDrawable(R.drawable.bg_et, this.theme)

//          execute if value of pass variable is empty
            if (password.toString().isEmpty()) {
                Log.d("message2", "Must be filled")     // for checking purposes
                val message = "Must be filled"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

            return Pair(null, background)
        }

//      function for sign in validation
        fun validSignIn() {
//          variable for saving return value from validation function
            val (message1, background1) = validEmail()
            val (message2, background2) = validPassword()

//          change value of helperText and background attributes in activity_signin layout
            etEmailLayout.helperText = message1
            etEmailLayout.editText?.background = background1
            etPasswordLayout.helperText = message2
            etPasswordLayout.editText?.background = background2

//          make variable for saving value of each function and return it in boolean data type
            val checkEmail = etEmailLayout.helperText == null
            val checkPassword = etPasswordLayout.helperText == null

//          for checking purposes
            Log.d("message1", checkEmail.toString())
            Log.d("message2", checkPassword.toString())

//          execute if value of checkEmail and checkPassword is null
            if (checkEmail && checkPassword) {
//              make toast to tell user that sign in is success
                Toast.makeText(this, "Sign In Success", Toast.LENGTH_SHORT).show()

//              make an intent with this activity context for execute MainActivity class
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

//          execute if value of checkEmail and checkPassword is not null
            else {
//              make toast to tell user that sign in is not success because email or password is incorrect
                Toast.makeText(this, "Email or Password Incorrect", Toast.LENGTH_SHORT).show()
            }
        }

//      to start event when click button with id btnSignIn
        btnSignIn.setOnClickListener {
//          execute validSignIn function
            validSignIn()

//          for checking purposes
            Log.d("email", email.toString())
            Log.d("password", password.toString())
        }

//      to start event when click area of text view with id linkSignUp
        linkSignUp.setOnClickListener {
//          make an intent with this activity context for execute SignupActivity class
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}