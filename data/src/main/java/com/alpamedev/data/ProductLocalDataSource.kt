package com.alpamedev.data

import com.alpamedev.domain.Product

interface ProductLocalDataSource {
    suspend fun requestProducts(): List<Product>
    suspend fun insertProduct(product: Product)
}