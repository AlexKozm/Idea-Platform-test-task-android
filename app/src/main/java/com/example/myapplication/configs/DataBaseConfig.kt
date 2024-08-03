package com.example.myapplication.configs

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.products.data.LocalDateTimeConverter
import com.example.myapplication.products.data.Product
import com.example.myapplication.products.data.ProductDao
import com.example.myapplication.products.data.TagsConverter

@Database(entities = [Product::class], version = 1)
@TypeConverters(TagsConverter::class, LocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    internal abstract fun getProductDao(): ProductDao
}

