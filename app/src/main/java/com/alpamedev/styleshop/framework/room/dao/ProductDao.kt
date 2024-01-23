package com.alpamedev.styleshop.framework.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alpamedev.styleshop.framework.room.entities.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductEntity")
    suspend fun requestProducts(): List<ProductEntity>

    @Insert
    suspend fun insertProducts(product: ProductEntity)
}