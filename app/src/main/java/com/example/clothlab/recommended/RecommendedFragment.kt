package com.example.clothlab.recommended

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothlab.responsivespacing.AdaptiveSpacingItemDecoration
import com.example.clothlab.product.ProductListAdapter
import com.example.clothlab.product.ProductListGlobalVar
import com.example.clothlab.R

class RecommendedFragment : Fragment(), View.OnClickListener {

    private lateinit var etSearch: EditText
    private lateinit var btnCart: ImageView
    private lateinit var recyclerProductRecommended: RecyclerView
    private lateinit var dataSetProduct: ArrayList<ProductListAdapter.Model>
    private lateinit var btnUnfocus: Button

    private var btnArray = arrayOfNulls<Button>(4)
    private var btnIdArray = intArrayOf(R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3)

    private var productListGlobalVar = ProductListGlobalVar()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recommended, container, false)
        etSearch = view.findViewById(R.id.etSearch)
        btnCart = view.findViewById(R.id.btnCart)
        recyclerProductRecommended = view.findViewById(R.id.recyclerProductRecommended)
        dataSetProduct = ArrayList()

        getProductData()

        for (i in btnArray.indices) {
            btnArray[i] = view.findViewById(btnIdArray[i])
            btnArray[i]!!.backgroundTintList = resources.getColorStateList(R.color.grey_button, context?.theme)
            btnArray[i]!!.setTextColor(resources.getColor(R.color.black_title, context?.theme))
            btnArray[i]!!.setOnClickListener(this)
        }

        btnUnfocus = btnArray[1]!!
        setFocus(btnUnfocus, btnArray[0]!!)
        Log.d("btnUnfocus", btnUnfocus.toString())

        return view
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
        btnUnfocus.backgroundTintList = resources.getColorStateList(R.color.grey_button, context?.theme)
        btnUnfocus.setTextColor(resources.getColor(R.color.black_title, context?.theme))
        btnFocus.backgroundTintList = resources.getColorStateList(R.color.green_primary, context?.theme)
        btnFocus.setTextColor(resources.getColor(R.color.white, context?.theme))
        Log.d("btnFocus", btnFocus.toString())
        this.btnUnfocus = btnFocus
        Log.d("btnUnfocus_setFocus", this.btnUnfocus.toString())
    }

    private fun populateData() {
        val spanCount = 2
        val size = 5
        val edgeEnabled = true
        val layoutManager = GridLayoutManager(activity, spanCount)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerProductRecommended.layoutManager = layoutManager
        recyclerProductRecommended.addItemDecoration(AdaptiveSpacingItemDecoration(size, edgeEnabled))
        recyclerProductRecommended.adapter = activity?.let { ProductListAdapter(it, dataSetProduct) }
    }

    private fun getProductData() {
        for (i in productListGlobalVar.imgProductArray.indices) {
            dataSetProduct.add(
                ProductListAdapter.Model(
                    productListGlobalVar.imgProductArray[i],
                    productListGlobalVar.textProductNameArray[i],
                    productListGlobalVar.textProductPriceArray[i].toString(),
                    productListGlobalVar.textProductRatingArray[i].toString()
                )
            )
            populateData()
        }
    }
}