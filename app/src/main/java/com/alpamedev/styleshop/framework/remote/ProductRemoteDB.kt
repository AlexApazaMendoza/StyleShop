package com.alpamedev.styleshop.framework.remote

import com.alpamedev.data.ProductRemoteDataSource
import com.alpamedev.domain.Product
import com.alpamedev.styleshop.framework.api.RetrofitConfig

class ProductRemoteDB(private val retrofitConfig: RetrofitConfig): ProductRemoteDataSource {
    override suspend fun requestProducts(): List<Product> {
        val response = retrofitConfig.productService.getProducts()
        if (response.isSuccessful) {
            return response.body()?.map { it.toProduct() } ?: emptyList()
        } else {
            throw Exception(response.message())
        }
    }
}