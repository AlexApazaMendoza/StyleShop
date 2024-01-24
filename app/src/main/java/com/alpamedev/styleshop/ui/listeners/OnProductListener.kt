package com.alpamedev.styleshop.ui.listeners

import com.alpamedev.domain.Product

interface OnProductListener {
    fun onClick(product: Product)
}