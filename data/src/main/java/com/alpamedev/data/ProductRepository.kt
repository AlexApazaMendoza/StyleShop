package com.alpamedev.data

import com.alpamedev.domain.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productLocalDataSource: ProductLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource
) {
    suspend fun requestProducts(): List<Product> {
        val local = productLocalDataSource.requestProducts()
        return local.ifEmpty {
            val remote = productRemoteDataSource.requestProducts()
            if (remote.isNotEmpty()) {
                remote.forEach { productLocalDataSource.insertProduct(it) }
            }
            remote
        }
    }
}