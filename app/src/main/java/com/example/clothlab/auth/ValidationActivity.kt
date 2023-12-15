package com.example.clothlab.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.clothlab.R

class ValidationActivity : AppCompatActivity() {

//  initiate every variable in this activity
    private lateinit var btnBack: ImageView
    private lateinit var etOTP1: EditText
    private lateinit var etOTP2: EditText
    private lateinit var etOTP3: EditText
    private lateinit var etOTP4: EditText
    private lateinit var etOTP5: EditText
    private lateinit var btnSend: Button
    private lateinit var linkResendCode: TextView

//  create life cycle for this activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)

//      declare every variable
        btnBack = findViewById(R.id.btnBack)
        etOTP1 = findViewById(R.id.etOTP1)
        etOTP2 = findViewById(R.id.etOTP2)
        etOTP3 = findViewById(R.id.etOTP3)
        etOTP4 = findViewById(R.id.etOTP4)
        etOTP5 = findViewById(R.id.etOTP5)
        btnSend = findViewById(R.id.btnSend)
        linkResendCode = findViewById(R.id.linkResendCode)

//      get text from edit text and return it in string form into local variable
        val etOtp1 = etOTP1.text
        val etOtp2 = etOTP2.text
        val etOtp3 = etOTP3.text
        val etOtp4 = etOTP4.text
        val etOtp5 = etOTP5.text

//      make an array of edit text for looping so that can move focus automatically
        val arrOTP = arrayOf(etOTP1, etOTP2, etOTP3, etOTP4, etOTP5)

//      make a 'for looping' for catch each value from arrOTP array variable
        for (currentEt in arrOTP.indices) {
//          to move focus from current edit text to the other when user type in edit text
            arrOTP[currentEt].addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}     // not used

//              request focus for next edit text after current edit text
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    nextEditText()?.requestFocus()
                }
                override fun afterTextChanged(s: Editable?) {}      // not used

//              function for looping edit text in array of arrOTP variable
                fun nextEditText(): EditText? {
//                  make a 'for looping' so that can return next edit text from arrOTP array variable
                    for (i in arrOTP.indices) {
                        if (arrOTP[i] == arrOTP[currentEt]) {
//                          execute if i index value equals 4
                            if (arrOTP[i] == arrOTP[4]) {
                                break
                            }
                            return arrOTP[i + 1]
                        }
                    }
                    return arrOTP[currentEt]
                }
            })
        }

//      function for OTP code validation
        fun validOTPCode() {
//          make variable for saving value of each variable that contain edit text value and return it in boolean data type
            val checkOtp1 = etOtp1 != null
            val checkOtp2 = etOtp2 != null
            val checkOtp3 = etOtp3 != null
            val checkOtp4 = etOtp4 != null
            val checkOtp5 = etOtp5 != null

//          execute if edit text with id etOTP1, etOTP2, etOTP3, etOTP4 and etOTP5 value is not null/empty
            if (checkOtp1 && checkOtp2 && checkOtp3 && checkOtp4 && checkOtp5) {
//              make an intent with this activity context for execute ValidationActivity class
                val intent = Intent(this, SigninActivity::class.java)
                startActivity(intent)
            }

//          execute if edit text with id etOTP1, etOTP2, etOTP3, etOTP4 and etOTP5 value is null/empty
            else {
                for (i in arrOTP.indices) {
                    if (arrOTP[i] == arrOTP[4]) {
                        break
                    }
                    arrOTP[i].background = resources.getDrawable(R.drawable.bg_et_failed, this.theme)
                }
            }
        }

//      to start event when click area of image view with id btnBack
        btnBack.setOnClickListener {
//          close this activity when it is done
            finish()
        }

//      to start event when click button with id btnSend
        btnSend.setOnClickListener {
//          execute validOTPCode function
            validOTPCode()

//          for checking purposes
            Log.d("et1", etOtp1.toString())
            Log.d("et2", etOtp2.toString())
            Log.d("et3", etOtp3.toString())
            Log.d("et4", etOtp4.toString())
            Log.d("et5", etOtp5.toString())
        }
    }
}