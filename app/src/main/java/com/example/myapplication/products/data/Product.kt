package com.example.myapplication.products.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.datetime.LocalDateTime

typealias Tags = List<String>

@Entity(tableName = "item")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val time: LocalDateTime,
//    @TypeConverters(TagsConverter::class)
    val tags: Tags,
    val amount: Int
)

