package com.example.clothlab.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.clothlab.R
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {

//  initiate every variable in this activity
    private lateinit var etFirstNameLayout: TextInputLayout
    private lateinit var etLastNameLayout: TextInputLayout
    private lateinit var etEmailLayout: TextInputLayout
    private lateinit var etPhoneNumberLayout: TextInputLayout
    private lateinit var etAddressLayout: TextInputLayout
    private lateinit var btnNext: Button
    private lateinit var linkSignIn: TextView

//  create life cycle for this activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

//      declare every variable
        etFirstNameLayout = findViewById(R.id.etFirstNameLayout)
        etLastNameLayout = findViewById(R.id.etLastNameLayout)
        etEmailLayout = findViewById(R.id.etEmailLayout)
        etPhoneNumberLayout = findViewById(R.id.etPhoneNumberLayout)
        etAddressLayout = findViewById(R.id.etAddressLayout)
        btnNext = findViewById(R.id.btnNext)
        linkSignIn = findViewById(R.id.linkSignIn)

//      get text from edit text in text input layout and return it in string form into variable
        val firstName = etFirstNameLayout.editText?.text
        val lastName = etLastNameLayout.editText?.text
        val email = etEmailLayout.editText?.text
        val phoneNumber = etPhoneNumberLayout.editText?.text
        val address = etAddressLayout.editText?.text        // not used yet

//      function for first name validation
        fun validFirstName(): Pair<String?, Drawable?> {
//          default background value
            val background = resources.getDrawable(R.drawable.bg_et, this.theme)

//          execute if value of firstName variable is empty
            if (firstName.toString().isEmpty()) {
                Log.d("message1", "Must be filled")     // for checking purposes
                val message = "Must be filled"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

//          execute if value of firstName variable is contained non-numeric characters
            else if (!firstName.toString().matches("^[A-Z]*(([',. ][a-zA-Z ])?[a-zA-Z.]*)*\$".toRegex())) {
                Log.d("message1", "Must be non-numeric characters")      // for checking purposes
                val message = "Must be non-numeric characters"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

//          execute if value of firstName variable is not started with upper-case character
            else if (!firstName.toString().matches("^[A-Z]+(([',. ][a-zA-Z ])?[a-zA-Z.]*)*\$".toRegex())) {
                Log.d("message1", "Must be started with upper-case character")      // for checking purposes
                val message = "Must be started with upper-case character"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

            return Pair(null, background)
        }

//      function for last name validation
        fun validLastName(): Pair<String?, Drawable?> {
//          default background value
            val background = resources.getDrawable(R.drawable.bg_et, this.theme)

//          execute if value of lastName variable is empty
            if (lastName.toString().isEmpty()) {
                return Pair(null, background)
            }

//          execute if value of lastName variable is contained non-numeric characters
            else if (!lastName.toString().matches("^[A-Z]*(([',. ][a-zA-Z ])?[a-zA-Z.]*)*\$".toRegex())) {
                Log.d("message2", "Must be non-numeric characters")      // for checking purposes
                val message = "Must be non-numeric characters"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

//          execute if value of lastName variable is not started with upper-case character
            else if (!lastName.toString().matches("^[A-Z]+(([',. ][a-zA-Z ])?[a-zA-Z.]*)*\$".toRegex())) {
                Log.d("message2", "Must be started with upper-case character")      // for checking purposes
                val message = "Must be started with upper-case character"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

            return Pair(null, background)
        }

//      function for email validation
        fun validEmail(): Pair<String?, Drawable?> {
//          default background value
            val background = resources.getDrawable(R.drawable.bg_et, this.theme)

//          execute if value of email variable is empty
            if (email.toString().isEmpty()) {
                Log.d("message3", "Must be filled")     // for checking purposes
                val message = "Must be filled"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

//          execute if value of email variable doesn't match the email patterns
            else if (!Patterns.EMAIL_ADDRESS.matcher(email.toString().trim()).matches()) {
                Log.d("message3", "Invalid email address")      // for checking purposes
                val message = "Invalid email address"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

            return Pair(null, background)
        }

//      function for phone number validation
        fun validPhoneNumber(): Pair<String?, Drawable?> {
//          default background value
            val background = resources.getDrawable(R.drawable.bg_et, this.theme)

//          execute if value of phoneNumber variable is empty
            if (phoneNumber.toString().isEmpty()) {
                return Pair(null, background)
            }

//          execute if value of phoneNumber variable is all digits
            else if (!phoneNumber.toString().matches("(^(\\+)[0-9]*)|[0-9]*".toRegex())) {
                Log.d("message4", "Must be all digits")     // for checking purposes
                val message = "Must be all digits"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

//          execute if value of phoneNumber variable is less than 10 digits
            else if (phoneNumber.toString().length < 10) {
                Log.d("message4", "Must be more than 10 digits")        // for checking purposes
                val message = "Must be more than 10 digits"
                val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                return Pair(message, background)
            }

            return Pair(null, background)
        }

//      function for sign up validation
        fun validSignUp() {
//          variable for saving return value from validation functions
            val (message1, background1) = validFirstName()
            val (message2, background2) = validLastName()
            val (message3, background3) = validEmail()
            val (message4, background4) = validPhoneNumber()

//          change value of helperText and background attributes in activity_signup layout
            etFirstNameLayout.helperText = message1
            etFirstNameLayout.editText?.background = background1
            etLastNameLayout.helperText = message2
            etLastNameLayout.editText?.background = background2
            etEmailLayout.helperText = message3
            etEmailLayout.editText?.background = background3
            etPhoneNumberLayout.helperText = message4
            etPhoneNumberLayout.editText?.background = background4

//          make variable for saving value of each function and return it in boolean data type
            val checkFirstName = etFirstNameLayout.helperText == null
            val checkLastName = etLastNameLayout.helperText == null
            val checkEmail = etEmailLayout.helperText == null
            val checkPhoneNumber = etPhoneNumberLayout.helperText == null

//          for checking purposes
            Log.d("message1", checkFirstName.toString())
            Log.d("message2", checkLastName.toString())
            Log.d("message3", checkEmail.toString())
            Log.d("message4", checkPhoneNumber.toString())

//          execute if value of checkFirstName, checkLastName, checkEmail, and checkPhoneNumber variable is null
            if (checkFirstName && checkLastName && checkEmail && checkPhoneNumber) {
//              make a toast to tell user that form is valid
                Toast.makeText(this, "Valid Form", Toast.LENGTH_SHORT).show()

//              make an intent with this activity context for execute PasswordActivity class
                val intent = Intent(this, PasswordActivity::class.java)
                startActivity(intent)
            }

//          execute if value of checkFirstName, checkLastName, checkEmail, and checkPhoneNumber variable is not null
            else {
//              make a toast to tell user that form is invalid
                Toast.makeText(this, "Invalid Form", Toast.LENGTH_SHORT).show()
            }
        }

//      to start event when click button with id btnNext
        btnNext.setOnClickListener {
//          execute validSignUp function
            validSignUp()

//          for checking purposes
            Log.d("firstname", firstName.toString())
            Log.d("lastname", lastName.toString())
            Log.d("email", email.toString())
            Log.d("phone", phoneNumber.toString())
        }

//      to start event when click area of text view with id linkSignIn
        linkSignIn.setOnClickListener {
//          make an intent with this activity context for execute SigninActivity class
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }
    }
}