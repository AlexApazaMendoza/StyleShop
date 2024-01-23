package com.alpamedev.styleshop.framework.local

import com.alpamedev.data.ProductLocalDataSource
import com.alpamedev.domain.Product
import com.alpamedev.styleshop.framework.room.dao.ProductDao
import com.alpamedev.styleshop.framework.room.toProductEntity

class ProductLocalDB(private val productDao: ProductDao): ProductLocalDataSource {
    override suspend fun requestProducts(): List<Product> {
        return productDao.requestProducts().map { it.toProduct() }
    }

    override suspend fun insertProduct(product: Product) {
        productDao.insertProducts(product.toProductEntity())
    }
}