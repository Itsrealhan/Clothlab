package com.example.clothlab.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.clothlab.R
import com.google.android.material.textfield.TextInputLayout

class PasswordActivity : AppCompatActivity() {

//  initiate every variable in this activity
    private lateinit var etPasswordLayout: TextInputLayout
    private lateinit var etValidatePasswordLayout: TextInputLayout
    private lateinit var btnBack: ImageView
    private lateinit var btnSubmit: Button

//  create life cycle for this activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

//      declare every variable
        etPasswordLayout = findViewById(R.id.etPasswordLayout)
        etValidatePasswordLayout = findViewById(R.id.etValidatePasswordLayout)
        btnBack = findViewById(R.id.btnBack)
        btnSubmit = findViewById(R.id.btnSubmit)

//      get text from edit text in text input layout and return it in string form into local variable
        val password = etPasswordLayout.editText?.text
        val validatePassword = etValidatePasswordLayout.editText?.text

//  function for password validation
    fun validPassword(): Pair<String?, Drawable?> {
//      default background value
        val background = resources.getDrawable(R.drawable.bg_et, this.theme)

//      execute if length of pass value is less than 8 characters
        if (password.toString().length < 8) {
            Log.d("message1", "Minimum 8 characters password")      // for checking purposes
            val message = "Minimum 8 characters password"
            val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
            return Pair(message, background)
        }

//      execute if value of pass variable doesn't contain a-z (a to z character)
        else if (!password.toString().matches(".*[a-z].*".toRegex())) {
            Log.d("message1","Must contain minimum 1 lower-case character")     // for checking purposes
            val message = "Must contain minimum 1 lower-case character"
            val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
            return Pair(message, background)
        }

//      execute if value of pass variable doesn't contain A-Z (A to Z character)
        else if (!password.toString().matches(".*[A-Z].*".toRegex())) {
            Log.d("message1","Must contain minimum 1 upper-case character")     // for checking purposes
            val message = "Must contain minimum 1 upper-case character"
            val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
            return Pair(message, background)
        }

//      execute if value of pass variable doesn't contain any special character below (~!@#$%^&*?><|)
        else if (!password.toString().matches(".*[~!@#$%^&*?><|].*".toRegex())) {
            Log.d("message1","Must contain minimum 1 special character")        // for checking purposes
            val message = "Must contain minimum 1 special character"
            val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
            return Pair(message, background)
        }

        return Pair(null, background)
    }

//  function for check again password
    fun validCheckPassword(): Pair<String?, Drawable?> {
//      default background value
        val background = resources.getDrawable(R.drawable.bg_et, this.theme)

//      execute if value of pass and validatePass variable is not same
        if (password.toString() != validatePassword.toString()) {
            Log.d("message2","Please put same password in validate password field")     // for checking purposes
            val message = "Please put same password in validate password field"
            val background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
            return Pair(message, background)
        }

        return Pair(null, background)
    }

//  function for submit password validation
    fun submitValidation() {
//      variable for saving return value from validation function
        val (message1, background1) = validPassword()
        val (message2, background2) = validCheckPassword()

//      change value of helperText and background attributes in activity_password layout
        etPasswordLayout.helperText = message1
        etPasswordLayout.editText?.background = background1
        etValidatePasswordLayout.helperText = message2
        etValidatePasswordLayout.editText?.background = background2

//      make variable for saving value of each function and return it in boolean data type
        val checkPassword = etPasswordLayout.helperText == null
        val checkValidatePassword = etValidatePasswordLayout.helperText == null

//      for checking purposes
        Log.d("message1", checkPassword.toString())
        Log.d("message2", checkValidatePassword.toString())

        if (checkPassword && checkValidatePassword) {
//          make an intent with this activity context for execute ValidationActivity class
            val intent = Intent(this, ValidationActivity::class.java)
            startActivity(intent)
        }

        else {
            Toast.makeText(this, "Password Validation Invalid", Toast.LENGTH_SHORT).show()
        }
    }

//      to start event when click area of image view with id btnBack
        btnBack.setOnClickListener {
//          close this activity when it is done
            finish()
        }

//      to start event when click button with id btnSubmit
        btnSubmit.setOnClickListener {
//          execute validPassword function
            submitValidation()

//          for checking purposes
            Log.d("password", password.toString())
            Log.d("validate password", validatePassword.toString())
        }
    }
}