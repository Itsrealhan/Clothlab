package com.example.clothlab.purchased

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothlab.responsivespacing.AdaptiveSpacingItemDecoration
import com.example.clothlab.product.ProductListAdapter
import com.example.clothlab.product.ProductListGlobalVar
import com.example.clothlab.R
import com.synnapps.carouselview.CarouselView

class PurchasedActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnDescriptionReadMore: RelativeLayout
    private lateinit var btnUnfocus: LinearLayout
    private lateinit var carouselProductImg: CarouselView
    private lateinit var btnBack: ImageView
    private lateinit var btnChat: ImageView
    private lateinit var btnFavorite: ImageView
    private lateinit var btnCart: ImageView
    private lateinit var btnMinus: ImageView
    private lateinit var btnAdd: ImageView
    private lateinit var imgArrowReadMore: ImageView
    private lateinit var btnCheckout: Button
    private lateinit var tvProductName: TextView
    private lateinit var tvProductPrice: TextView
    private lateinit var tvProductRating: TextView
    private lateinit var tvProductTotalPieces: TextView
    private lateinit var tvProductDescription: TextView
    private lateinit var tvReadMore: TextView
    private lateinit var recyclerThisProduct: RecyclerView
//    private lateinit var recyclerProductVariation: RecyclerView
//    private lateinit var recyclerProductSize: RecyclerView
    private lateinit var recyclerProductPurchased: RecyclerView
    private lateinit var dataSetProduct: ArrayList<ProductListAdapter.Model>
    private lateinit var dataSetProductVariation: ArrayList<ProductVariationAdapter.Model>
    private lateinit var dataSetProductPurchased: ArrayList<PurchasedActivityProductListAdapter.Model>

    private var productListGlobalVar = ProductListGlobalVar()
    private var imgArray = arrayOfNulls<Int>(6)
    private val textProductVariationArray = arrayOf("Red", "Orange", "Blue", "Green")
    private val textProductSizeArray = arrayOf("S", "M", "XL", "XXL")
    private var totalPieces = 1
    private var btnVariationArray = arrayOfNulls<LinearLayout>(3)
    private var btnSizeArray = arrayOfNulls<RadioButton>(3)
    private var imgVariationNullArray = arrayOfNulls<ImageView>(3)
    private val imgVariationArray = arrayOf(
        R.id.imgVariation0,
        R.id.imgVariation1,
        R.id.imgVariation2
    )
    private val btnIdVariationArray = intArrayOf(
        R.id.btnVariation0,
        R.id.btnVariation1,
        R.id.btnVariation2
    )
    private val btnIdSizeArray = intArrayOf(R.id.btnSize0, R.id.btnSize1, R.id.btnSize2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchased)

        btnDescriptionReadMore = findViewById(R.id.btnDescriptionReadMore)
        carouselProductImg = findViewById(R.id.carouselProductImg)
        btnBack = findViewById(R.id.btnBack)
        btnChat = findViewById(R.id.btnChat)
        btnFavorite = findViewById(R.id.btnFavorite)
        btnCart = findViewById(R.id.btnCart)
        btnMinus = findViewById(R.id.btnMinus)
        btnAdd = findViewById(R.id.btnAdd)
        imgArrowReadMore = findViewById(R.id.imgArrowReadMore)
        btnCheckout = findViewById(R.id.btnCheckout)
        tvProductName = findViewById(R.id.tvProductName)
        tvProductPrice = findViewById(R.id.tvProductPrice)
        tvProductRating = findViewById(R.id.tvProductRating)
        tvProductTotalPieces = findViewById(R.id.tvProductTotalPieces)
        tvProductDescription = findViewById(R.id.tvProductDescription)
        tvReadMore = findViewById(R.id.tvReadMore)
        recyclerThisProduct = findViewById(R.id.recyclerThisProduct)
//        recyclerProductVariation = findViewById(R.id.recyclerProductVariation)
//        recyclerProductSize = findViewById(R.id.recyclerProductSize)
        recyclerProductPurchased = findViewById(R.id.recyclerProductPurchased)
        dataSetProduct = ArrayList()
        dataSetProductVariation = ArrayList()
        dataSetProductPurchased = ArrayList()

        btnBack.setOnClickListener {
            finish()
        }

        btnChat.setOnClickListener {

        }

        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        btnMinus.setOnClickListener {
            if (totalPieces > 1) {
                totalPieces--
                tvProductTotalPieces.text = totalPieces.toString()
            }
        }

        btnAdd.setOnClickListener {
            if (totalPieces < 10) {
                totalPieces++
                tvProductTotalPieces.text = totalPieces.toString()
            }
        }

        btnCheckout.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }

        var isChecked = true
        btnDescriptionReadMore.setOnClickListener {
            if (isChecked == true) {
                tvProductDescription.maxLines = Int.MAX_VALUE
                tvReadMore.text = "Read Less"
                imgArrowReadMore.rotation = 270F
                imgArrowReadMore.setPadding(0, 0, 6, 0)
                isChecked = false
            } else {
                tvProductDescription.maxLines = 4
                tvReadMore.text = "Read More"
                imgArrowReadMore.rotation = 90F
                imgArrowReadMore.setPadding(4, 0, 0, 0)
                isChecked = true
            }
        }

        for (i in btnVariationArray.indices) {
            btnVariationArray[i] = findViewById(btnIdVariationArray[i])
            var variation = btnVariationArray[i]?.setOnClickListener(this)
        }

        for (i in btnSizeArray.indices) {
            btnSizeArray[i] = findViewById(btnIdSizeArray[i])
            btnSizeArray[i]?.setOnCheckedChangeListener { buttonView, isChecked ->  }
        }

        btnUnfocus = btnVariationArray[0]!!

        getDataCarousel()
        getData()
//        getDataVariation()
        getDataThisProduct()
        getDataProduct()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnVariation0 -> setFocus(btnUnfocus, btnVariationArray[0]!!)
            R.id.btnVariation1 -> setFocus(btnUnfocus, btnVariationArray[1]!!)
            R.id.btnVariation2 -> setFocus(btnUnfocus, btnVariationArray[2]!!)
        }
    }

    private fun setFocus(btnUnfocus: LinearLayout, btnFocus: LinearLayout) {
        btnUnfocus.background = resources.getDrawable(R.drawable.bg_btn_variation, theme)
        btnFocus.background = resources.getDrawable(R.drawable.bg_btn_variation_selected, theme)
        this.btnUnfocus = btnFocus
    }

    private fun makeCarousel() {
        carouselProductImg.pageCount = imgArray.size

        carouselProductImg.setImageListener { position, imageView ->
            imgArray[position]?.let { imageView.setImageResource(it) }
        }
    }

    private fun getDataCarousel() {
        when (intent.getStringExtra("product_name").toString()) {
            "T-shirt" -> for (i in 0..5) {
                imgArray[i] = productListGlobalVar.imgProductArray[0]
            }

            "Trousers" -> for (i in 0..5) {
                imgArray[i] = productListGlobalVar.imgProductArray[1]
            }

            "Hat" -> for (i in 0..5) {
                imgArray[i] = productListGlobalVar.imgProductArray[2]
            }

            "Shoes" -> for (i in 0..5) {
                imgArray[i] = productListGlobalVar.imgProductArray[3]
            }

            "Jacket" -> for (i in 0..5) {
                imgArray[i] = productListGlobalVar.imgProductArray[4]
            }
        }
        makeCarousel()
    }

    private fun getData() {
        tvProductName.text = intent.getStringExtra("product_name").toString()
        tvProductPrice.text = intent.getStringExtra("product_price").toString()
        tvProductRating.text = intent.getStringExtra("product_rating").toString()
    }

    private fun populateDataThisProduct() {
        val size = 30
        val edgeEnabled = false
        val layoutManager = LinearLayoutManager(this)

        layoutManager.orientation = RecyclerView.HORIZONTAL
        recyclerThisProduct.layoutManager = layoutManager
        recyclerThisProduct.addItemDecoration(AdaptiveSpacingItemDecoration(size, edgeEnabled))
        recyclerThisProduct.adapter = PurchasedActivityProductListAdapter(this, dataSetProductPurchased)
    }

    private fun getDataThisProduct() {
        var img = 0
        var name = "0"
        var price = "0"
        var rating = "0"

        when (intent.getStringExtra("product_name").toString()) {
            "T-shirt" -> {
                img = productListGlobalVar.imgProductArray[0]
                name = productListGlobalVar.textProductNameArray[0]
                price = productListGlobalVar.textProductPriceArray[0].toString()
                rating = productListGlobalVar.textProductRatingArray[0].toString()
            }

            "Trousers" -> {
                img  = productListGlobalVar.imgProductArray[1]
                name = productListGlobalVar.textProductNameArray[1]
                price = productListGlobalVar.textProductPriceArray[1].toString()
                rating = productListGlobalVar.textProductRatingArray[1].toString()
            }

            "Hat" -> {
                img  = productListGlobalVar.imgProductArray[2]
                name = productListGlobalVar.textProductNameArray[2]
                price = productListGlobalVar.textProductPriceArray[2].toString()
                rating = productListGlobalVar.textProductRatingArray[2].toString()
            }

            "Shoes" -> {
                img  = productListGlobalVar.imgProductArray[3]
                name = productListGlobalVar.textProductNameArray[3]
                price = productListGlobalVar.textProductPriceArray[3].toString()
                rating = productListGlobalVar.textProductRatingArray[3].toString()
            }

            "Jacket" -> {
                img  = productListGlobalVar.imgProductArray[4]
                name = productListGlobalVar.textProductNameArray[4]
                price = productListGlobalVar.textProductPriceArray[4].toString()
                rating = productListGlobalVar.textProductRatingArray[4].toString()
            }
        }

//        for (i in imgVariationNullArray.indices) {
//            imgVariationNullArray[i] = findViewById(imgVariationArray[i])
//            imgVariationNullArray[i]?.setImageResource(img)
//        }

        for (i in 0..5) {
            dataSetProductPurchased.add(
                PurchasedActivityProductListAdapter.Model(
                    img,
                    name,
                    price,
                    rating
                )
            )
        }
        populateDataThisProduct()
    }

    private fun populateDataProduct() {
        val spanCount = 2
        val size = 36
        val edgeEnabled = false
        val layoutManager = GridLayoutManager(this, spanCount)

        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerProductPurchased.layoutManager = layoutManager
        recyclerProductPurchased.addItemDecoration(AdaptiveSpacingItemDecoration(size, edgeEnabled))
        recyclerProductPurchased.adapter = ProductListAdapter(this, dataSetProduct)
    }

    private fun getDataProduct() {
        for (i in productListGlobalVar.imgProductArray.indices) {
            dataSetProduct.add(
                ProductListAdapter.Model(
                    productListGlobalVar.imgProductArray[i],
                    productListGlobalVar.textProductNameArray[i],
                    productListGlobalVar.textProductPriceArray[i].toString(),
                    productListGlobalVar.textProductRatingArray[i].toString()
                )
            )
        }
        populateDataProduct()
    }

//    private fun populateDataVariation() {
//        val size = 20
//        val edgeEnabled = false
//        val layoutManager = LinearLayoutManager(this)
//
//        layoutManager.orientation = RecyclerView.HORIZONTAL
//        recyclerProductVariation.layoutManager = layoutManager
//        recyclerProductVariation.addItemDecoration(AdaptiveSpacingItemDecoration(size, edgeEnabled))
//        recyclerProductVariation.adapter = ProductVariationAdapter(this, dataSetProductVariation)
//    }
//
//    private fun getDataVariation() {
//        var imgProduct = 0
//        when (intent.getStringExtra("product_name").toString()) {
//            "T-shirt" ->  imgProduct = productListGlobalVar.imgProductArray[0]
//
//            "Trousers" -> imgProduct = productListGlobalVar.imgProductArray[1]
//
//            "Hat" -> imgProduct = productListGlobalVar.imgProductArray[2]
//
//            "Shoes" -> imgProduct = productListGlobalVar.imgProductArray[3]
//
//            "Jacket" -> imgProduct = productListGlobalVar.imgProductArray[4]
//        }
//
//        for (i in textProductVariationArray.indices) {
//            dataSetProductVariation.add(
//                ProductVariationAdapter.Model(
//                    imgProduct,
//                    textProductVariationArray[i]
//                )
//            )
//        }
//
//        populateDataVariation()
//    }
}


//        btnDescriptionViewMore.addOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                tvProductDescription.maxLines = 10
//            } else {
//                tvProductDescription.maxLines = 4
//            }
//        }

//        val iconReadMore: Int = R.drawable.ic_arrow_next
//        iconReadMore.rotateRight(90)
//        val labelReadMore: Spannable = SpannableString("Read More  ")
//        labelReadMore.setSpan(
//            ImageSpan(this, iconReadMore, ImageSpan.ALIGN_CENTER), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        )
//        btnDescriptionViewMore.textOn = labelReadMore
//        btnDescriptionViewMore.textOff = labelReadMore