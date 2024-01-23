package com.alpamedev.styleshop.framework.api

import com.alpamedev.styleshop.framework.api.entities.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products/")
    suspend fun getProducts(): Response<List<ProductResponse>>
}