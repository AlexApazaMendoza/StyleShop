package com.alpamedev.usecases

import com.alpamedev.data.ProductRepository
import com.alpamedev.domain.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductUseCase(private val productRepository: ProductRepository) {
    suspend fun getProducts(): List<Product> = withContext(Dispatchers.IO) {
        productRepository.requestProducts()
    }
}