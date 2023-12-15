package com.example.clothlab.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothlab.responsivespacing.AdaptiveSpacingItemDecoration
import com.example.clothlab.product.ProductListAdapter
import com.example.clothlab.R


class SubmenuActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnBack: ImageView
    private lateinit var btnSearch: ImageView
    private lateinit var tvMenuProductName: TextView
    private lateinit var buttonTypeButtonView: LinearLayout
    private lateinit var recyclerProductSubmenu: RecyclerView
    private lateinit var dataSetProduct: ArrayList<ProductListAdapter.Model>
    private lateinit var btnUnfocus: Button

    private var btnArray = arrayOfNulls<Button>(4)
    private var btnIdArray = intArrayOf(R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submenu)

        btnBack = findViewById(R.id.btnBack)
        btnSearch = findViewById(R.id.btnSearch)
        tvMenuProductName = findViewById(R.id.tvMenuProductName)
        buttonTypeButtonView = findViewById(R.id.buttonTypeButtonView)
        recyclerProductSubmenu = findViewById(R.id.recyclerProductSubmenu)
        dataSetProduct = ArrayList()

        tvMenuProductName.text = intent.getStringExtra("product_name").toString()

        getData()

        btnBack.setOnClickListener {
            finish()
        }

        for (i in btnArray.indices) {
            btnArray[i] = findViewById(btnIdArray[i])
            btnArray[i]?.setOnClickListener(this)
        }

        btnUnfocus = btnArray[1]!!
        setFocus(btnUnfocus, btnArray[0]!!)
        Log.d("btnUnfocus", btnUnfocus.toString())
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn0 -> setFocus(btnUnfocus, btnArray[0]!!)
            R.id.btn1 -> setFocus(btnUnfocus, btnArray[1]!!)
            R.id.btn2 -> setFocus(btnUnfocus, btnArray[2]!!)
            R.id.btn3 -> setFocus(btnUnfocus, btnArray[3]!!)
        }
    }

    private fun setFocus(btnUnfocus: Button, btnFocus: Button) {
        btnUnfocus.setTextColor(resources.getColor(R.color.black_title, theme))
        btnUnfocus.backgroundTintList = resources.getColorStateList(R.color.grey_button, theme)
        btnFocus.setTextColor(resources.getColor(R.color.white, theme))
        btnFocus.backgroundTintList = resources.getColorStateList(R.color.green_primary, theme)
        this.btnUnfocus = btnFocus
    }

    private fun populateData() {
        val spanCount = 2
        val size = 5
        val edgeEnabled = true
        val layoutManager = GridLayoutManager(this, spanCount)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerProductSubmenu.layoutManager = layoutManager
        recyclerProductSubmenu.addItemDecoration(AdaptiveSpacingItemDecoration(size, edgeEnabled))
        recyclerProductSubmenu.adapter = ProductListAdapter(this, dataSetProduct)
    }

    private fun getData() {
        for (i in 1..8) {
            dataSetProduct.add(
                ProductListAdapter.Model(
                    intent.getIntExtra("product_image", 0),
                    intent.getStringExtra("product_name").toString(),
                    intent.getStringExtra("product_price").toString(),
                    intent.getStringExtra("product_rating").toString(),
                )
            )
            populateData()
        }
    }
}