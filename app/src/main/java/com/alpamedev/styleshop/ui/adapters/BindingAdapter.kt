package com.alpamedev.styleshop.ui.adapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun setIsVisible(view: View, isVisible:Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}