package com.alpamedev.styleshop.framework.room

import androidx.room.TypeConverter
import com.alpamedev.styleshop.framework.room.entities.CategoryEntity
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun stringListToJson(value: List<String>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun categoryEntityToJson(value: CategoryEntity) = Gson().toJson(value)

    @TypeConverter
    fun jsonToCategoryEntity(value: String) = Gson().fromJson(value, CategoryEntity::class.java)
}