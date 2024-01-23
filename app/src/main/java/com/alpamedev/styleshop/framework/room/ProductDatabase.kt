package com.alpamedev.styleshop.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alpamedev.styleshop.framework.room.dao.ProductDao
import com.alpamedev.styleshop.framework.room.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [Converters::class])
abstract class ProductDatabase: RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "products.db"
        private var instance: ProductDatabase? = null

        private fun create(context: Context): ProductDatabase =
            Room.databaseBuilder(context, ProductDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): ProductDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun getProductDao(): ProductDao
}