package com.alpamedev.styleshop.framework.room

import com.alpamedev.domain.Category
import com.alpamedev.domain.Product
import com.alpamedev.styleshop.framework.room.entities.CategoryEntity
import com.alpamedev.styleshop.framework.room.entities.ProductEntity

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
        id,
        title,
        price,
        description,
        category.toCategoryEntity(),
        images,
        creationAt,
        updatedAt
    )
}

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id,
        image,
        name,
        creationAt,
        updatedAt
    )
}