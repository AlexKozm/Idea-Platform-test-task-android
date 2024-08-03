package com.example.data.products.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.products.converters.LocalDateTimeConverter
import com.example.data.products.data.Product
import com.example.data.products.data.ProductDao
import com.example.data.products.converters.TagsConverter

@Database(entities = [Product::class], version = 1)
@TypeConverters(TagsConverter::class, LocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    internal abstract fun getProductDao(): ProductDao
}

