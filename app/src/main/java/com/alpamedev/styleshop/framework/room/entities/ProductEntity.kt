package com.alpamedev.styleshop.framework.room.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import com.alpamedev.domain.Category
import com.alpamedev.domain.Product

@Entity(primaryKeys = ["id"], tableName = "ProductEntity")
data class ProductEntity(
    @NonNull val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val category: CategoryEntity,
    val images: List<String>,
    val creationAt: String,
    val updatedAt: String
){
    fun toProduct() = Product(
        id,
        title,
        price,
        description,
        category.toCategory(),
        images,
        creationAt,
        updatedAt
    )
}

data class CategoryEntity(
    val id: Int,
    val image: String,
    val name: String,
    val creationAt: String,
    val updatedAt: String
) {
    fun toCategory() = Category(
        id,
        image,
        name,
        creationAt,
        updatedAt
    )
}