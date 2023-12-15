package com.example.clothlab.purchased

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.clothlab.R
import com.squareup.picasso.Picasso

class PurchasedActivityProductListAdapter(private val context: Context, private val dataSet: ArrayList<Model>): RecyclerView.Adapter<PurchasedActivityProductListAdapter.ViewHolder>() {

    data class Model(
        val productImg: Int,
        val productName: String,
        val productPrice: String,
        val productRating: String,
    )

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardProduct: CardView = view.findViewById(R.id.cardProductSmall)
        val imgProduct: ImageView = view.findViewById(R.id.imgProduct)
        val tvProductName: TextView = view.findViewById(R.id.tvProductName)
        val tvProductPrice: TextView = view.findViewById(R.id.tvProductPrice)
        val tvProductRating: TextView = view.findViewById(R.id.tvProductRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_small, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataSet[position]

        Picasso.get()
            .load(currentItem.productImg)
            .into(holder.imgProduct)
        holder.tvProductName.text = currentItem.productName
        holder.tvProductPrice.text = currentItem.productPrice
        holder.tvProductRating.text = currentItem.productRating

        holder.cardProduct.setOnClickListener {
            val intent = Intent(context, PurchasedActivity::class.java)
            intent.putExtra("product_image", currentItem.productImg)
            intent.putExtra("product_name", currentItem.productName)
            intent.putExtra("product_price", currentItem.productPrice)
            intent.putExtra("product_rating", currentItem.productRating)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}