package com.alpamedev.data

import com.alpamedev.domain.Product

interface ProductRemoteDataSource {
    suspend fun requestProducts(): List<Product>
}