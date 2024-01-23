package com.alpamedev.styleshop.framework.api.entities

import com.alpamedev.domain.Category

data class CategoryResponse(
    val id: Int,
    val image: String,
    val name: String,
    val creationAt: String,
    val updatedAt: String
){
    fun toCategory(): Category {
        return Category(id, image, name, creationAt, updatedAt)
    }
}
