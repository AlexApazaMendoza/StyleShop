package com.alpamedev.styleshop.framework.api.entities

import com.alpamedev.domain.Product
import com.alpamedev.styleshop.framework.formatImageUrl

data class ProductResponse(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val category: CategoryResponse,
    val images: List<String>,
    val creationAt: String,
    val updatedAt: String
) {
    fun toProduct(): Product {
        return Product(
            id,
            title,
            price,
            description,
            category.toCategory(),
            images.map { formatImageUrl(it) },
            creationAt,
            updatedAt
        )
    }
}