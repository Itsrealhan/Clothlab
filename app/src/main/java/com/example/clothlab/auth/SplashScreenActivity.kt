package com.example.clothlab.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.clothlab.R

class SplashScreenActivity : AppCompatActivity() {

//  create life cycle for this activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//      make event handler with looper that will finish after 1500 ms (1.5 s)
        Handler(Looper.getMainLooper()).postDelayed({
//          make an intent with this activity context for execute SigninActivity class
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)

//          close this activity when it is done
            finish()
        }, 1500)
    }
}