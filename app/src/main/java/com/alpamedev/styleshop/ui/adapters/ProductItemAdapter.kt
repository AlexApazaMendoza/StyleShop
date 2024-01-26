package com.alpamedev.styleshop.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alpamedev.domain.Product
import com.alpamedev.styleshop.R
import com.alpamedev.styleshop.databinding.ProductRowItemBinding
import com.alpamedev.styleshop.ui.listeners.OnProductListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ProductItemAdapter(
    val listener: OnProductListener
): RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder>() {

    private lateinit var mContext: Context
    private val dataSet: MutableList<Product> = mutableListOf()
    inner class ProductItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ProductRowItemBinding.bind(view)

        fun setUpListener(product: Product) {
            binding.root.setOnClickListener {
                listener.onClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        mContext = parent.context
        return ProductItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_row_item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        with(holder) {
            val product = dataSet[position]
            binding.product = product
            setUpListener(product)
            Glide.with(mContext)
                .load(product.images[0])
                .placeholder(R.drawable.image_no_available)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.ivProduct)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(it: List<Product>) {
        dataSet.clear()
        dataSet.addAll(it)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortList() {
        dataSet.sortBy { it.price }
        notifyDataSetChanged()
    }
}