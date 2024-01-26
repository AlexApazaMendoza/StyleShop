package com.alpamedev.styleshop.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alpamedev.styleshop.R
import com.alpamedev.styleshop.databinding.CarouselItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ProductCarouselAdapter: RecyclerView.Adapter<ProductCarouselAdapter.ProductCarouselViewHolder>() {

    private lateinit var mContext: Context
    private val dataSet: MutableList<String> = mutableListOf()

    inner class ProductCarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CarouselItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCarouselViewHolder {
        mContext = parent.context
        return ProductCarouselViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false))

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ProductCarouselViewHolder, position: Int) {
        with(holder) {
            Glide.with(mContext)
                .load(dataSet[position])
                .placeholder(R.drawable.image_no_available)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.ivProduct)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(it: List<String>) {
        dataSet.clear()
        dataSet.addAll(it)
        notifyDataSetChanged()
    }
}