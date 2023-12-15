package com.example.clothlab.purchased

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clothlab.R
import com.squareup.picasso.Picasso

class ProductVariationAdapter(val context: Context, val dataSet: ArrayList<Model>): RecyclerView.Adapter<ProductVariationAdapter.ViewHolder>() {

    data class Model(
        val productImg: Int,
        val productName: String
    )

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val rbtnVariationSize: RadioButton = view.findViewById(R.id.rbtnVariationSize)
        val imgProductVariation: ImageView = view.findViewById(R.id.imgProductVariation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_variation_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataSet[position]

        Picasso.get()
            .load(currentItem.productImg)
            .into(holder.imgProductVariation)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}