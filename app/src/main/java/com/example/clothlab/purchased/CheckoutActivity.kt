package com.example.clothlab.purchased

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.clothlab.R

class CheckoutActivity : AppCompatActivity() {

    private lateinit var btnChangeAddresseeDetails: RelativeLayout
    private lateinit var btnVoucher: RelativeLayout
    private lateinit var btnPaymentMethods: RelativeLayout
    private lateinit var btnShipmentOptions: LinearLayout
    private lateinit var btnBack: ImageView
    private lateinit var btnChat: ImageView
    private lateinit var imgProduct: ImageView
    private lateinit var tvProductName: TextView
    private lateinit var tvProductVariant: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var tvProductQty: TextView
    private lateinit var tvAddresseeNameAndPhone: TextView
    private lateinit var tvProductSubtotal: TextView
    private lateinit var tvShipmentSubtotal: TextView
    private lateinit var tvServiceFee: TextView
    private lateinit var tvSecurityFee: TextView
    private lateinit var tvTotalPaymentBottom: TextView
    private lateinit var tvUserMessage: EditText
    private lateinit var btnBuyNow: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        btnChangeAddresseeDetails = findViewById(R.id.btnChangeAddresseeDetails)
        btnVoucher = findViewById(R.id.btnVoucher)
        btnPaymentMethods = findViewById(R.id.btnPaymentMethods)
        btnShipmentOptions = findViewById(R.id.btnShipmentOptions)
        btnBack = findViewById(R.id.btnBack)
        btnChat = findViewById(R.id.btnChat)
        imgProduct = findViewById(R.id.imgProduct)
        tvProductName = findViewById(R.id.tvProductName)
        tvProductVariant = findViewById(R.id.tvProductVariant)
        tvProductPrice = findViewById(R.id.tvProductPrice)
        tvProductQty = findViewById(R.id.tvProductQty)
        tvAddresseeNameAndPhone = findViewById(R.id.tvAddresseeNameAndPhone)
        tvProductSubtotal = findViewById(R.id.tvProductSubtotal)
        tvShipmentSubtotal = findViewById(R.id.tvShipmentSubtotal)
        tvServiceFee = findViewById(R.id.tvServiceFee)
        tvSecurityFee = findViewById(R.id.tvSecurityFee)
        tvTotalPaymentBottom = findViewById(R.id.tvTotalPaymentBottom)
        tvUserMessage = findViewById(R.id.tvUserMessage)
        btnBuyNow = findViewById(R.id.btnBuyNow)

        btnBack.setOnClickListener { finish() }
    }
}