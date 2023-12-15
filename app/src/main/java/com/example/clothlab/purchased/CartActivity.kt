package com.example.clothlab.purchased

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.clothlab.R

class CartActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var recyclerProductCart: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        btnBack = findViewById(R.id.btnBack)
        recyclerProductCart = findViewById(R.id.recyclerProductCart)

        btnBack.setOnClickListener {
            finish()
        }

    }
}