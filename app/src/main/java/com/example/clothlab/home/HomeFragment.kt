package com.example.clothlab.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothlab.responsivespacing.AdaptiveSpacingItemDecoration
import com.example.clothlab.purchased.CartActivity
import com.example.clothlab.product.ProductListAdapter
import com.example.clothlab.product.ProductListGlobalVar
import com.example.clothlab.R
import com.example.clothlab.responsivespacing.RecyclerViewItemSpacing
import com.synnapps.carouselview.CarouselView

class HomeFragment : Fragment() {

//  initiate every variable in this fragment
    private lateinit var carouselAdsBanner: CarouselView
    private lateinit var btnCart: ImageView
    private lateinit var etSearch: SearchView
    private lateinit var recyclerMenuProductHome: RecyclerView
    private lateinit var recyclerProductHome: RecyclerView
    private lateinit var dataSetMenuProduct: ArrayList<HomeFragmentMenuProductListAdapter.Model>
    private lateinit var dataSetProduct: ArrayList<ProductListAdapter.Model>

    private var productListGlobalVar = ProductListGlobalVar()

//  make an array for image in carousel view
    private var imgAdsArray = arrayOf(
    R.drawable.ads1,
    R.drawable.ads2,
    R.drawable.ads3
    )

//  create life cycle for this fragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

//      declare every variable
        btnCart = view.findViewById(R.id.btnCart)
        carouselAdsBanner = view.findViewById(R.id.carouselAdsBanner)
        recyclerProductHome = view.findViewById(R.id.recyclerProductHome)
        recyclerMenuProductHome = view.findViewById(R.id.recyclerMenuProductHome)
        dataSetProduct = ArrayList()
        dataSetMenuProduct = ArrayList()

        btnCart.setOnClickListener {
            val intent = Intent(context, CartActivity::class.java)
            startActivity(intent)
        }

//      execute makeCarousel function
        makeCarousel()

//      execute getDataMenu function
        getDataMenu()

//      execute getDataProduct function
        getDataProduct()

//      Inflate the layout for this fragment
        return view
    }

    private fun makeCarousel() {
//      set page count for carousel ads banner
        carouselAdsBanner.pageCount = imgAdsArray.size

//      to change image when the image in carousel is slid
        carouselAdsBanner.setImageListener { position, imageView ->
            imageView.setImageResource(imgAdsArray[position])
        }
    }

    private fun populateMenu() {
        val spanCount = 5
        val spacingItem = 7
        val includeEdge = false
        val layoutManager = LinearLayoutManager(activity)

        layoutManager.orientation = RecyclerView.HORIZONTAL
        recyclerMenuProductHome.layoutManager = layoutManager
        recyclerMenuProductHome.addItemDecoration(RecyclerViewItemSpacing(spanCount, spacingItem, includeEdge))
        recyclerMenuProductHome.adapter = context?.let { HomeFragmentMenuProductListAdapter(it, dataSetMenuProduct) }
    }

    private fun getDataMenu() {
        for (i in productListGlobalVar.imgProductMenuArray.indices) {
            dataSetMenuProduct.add(
                HomeFragmentMenuProductListAdapter.Model(
                    productListGlobalVar.imgProductMenuArray[i],
                    productListGlobalVar.imgProductArray[i],
                    productListGlobalVar.textProductMenuNameArray[i],
                    productListGlobalVar.textProductPriceArray[i].toString(),
                    productListGlobalVar.textProductRatingArray[i].toString()
                )
            )
            populateMenu()
        }
    }

    private fun populateProduct() {
        val spanCount = 2
        val size = 6
        val edgeEnabled = true
        val layoutManager = GridLayoutManager(activity, spanCount)

        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerProductHome.layoutManager = layoutManager
        recyclerProductHome.addItemDecoration(AdaptiveSpacingItemDecoration(size, edgeEnabled))
        recyclerProductHome.adapter = context?.let { ProductListAdapter(it, dataSetProduct) }
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
            populateProduct()
        }
    }
}



//    private lateinit var menuItem: MenuItem

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.search_menu, menu)
//        etSearch = MenuItemCompat.getActionView(menuItem) as SearchView
//
//        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        etSearch.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
//
//        etSearch.setOnQueryTextListener(object : OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                doMySearch(query)
//                return false
//            }
//            override fun onQueryTextChange(query: String): Boolean {
//                doMySearch(query)
//                return false
//            }
//        })
//
//        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        (menu.findItem(R.id.action_search).actionView as SearchView).apply {
//            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
//            isIconifiedByDefault = false
//        }
//
//        val item = menu.findItem(R.id.action_search)
//        val searchView = SearchView(activity as MainActivity)
//    }

//    private fun doMySearch(query: String) {
//        for (i in imgProductArray.indices) {
//            dataSetProduct.add(
//                ProductListAdapter.Model(
//                    imgProductArray[i],
//                    textProductNameArray[i],
//                    textProductPriceArray[i].toString(),
//                    textProductRatingArray[i].toString()
//                )
//            )
//            populateProduct()
//        }
//    }