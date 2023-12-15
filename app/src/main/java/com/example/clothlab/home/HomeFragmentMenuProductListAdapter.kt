package com.example.clothlab.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.clothlab.R

class HomeFragmentMenuProductListAdapter(private val context: Context, private val dataSet: ArrayList<Model>): RecyclerView.Adapter<HomeFragmentMenuProductListAdapter.ViewHolder>() {

    data class Model(
        val productMenuImg: Int,
        val productImg: Int,
        val productMenuName: String,
        val productPrice: String,
        val productRating: String,
    )

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardMenuProduct: LinearLayout = view.findViewById(R.id.cardMenuProduct)
        val imgMenuProduct: ImageView = view.findViewById(R.id.imgMenuProduct)
        val tvMenuProductName: TextView = view.findViewById(R.id.tvMenuProductName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataSet[position]

        val imageLoader = ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()

        val request = ImageRequest.Builder(context)
            .data(currentItem.productMenuImg)
            .target(holder.imgMenuProduct)
            .build()
        imageLoader.enqueue(request)

        holder.tvMenuProductName.text = currentItem.productMenuName
        holder.cardMenuProduct.setOnClickListener {
            val intent = Intent(context, SubmenuActivity::class.java)
            intent.putExtra("product_image", currentItem.productImg)
            intent.putExtra("product_name", currentItem.productMenuName)
            intent.putExtra("product_price", currentItem.productPrice)
            intent.putExtra("product_rating", currentItem.productRating)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}