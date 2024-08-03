package com.example.myapplication.products.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


//@ProvidedTypeConverter
class TagsConverter {
    @TypeConverter
    fun toTags(string: String): Tags =
        if (string == "[]") emptyList()
        else string
//            .substringAfter("[")
//            .substringBeforeLast("]")
            .dropWhile { it in "[\"" }
            .dropLastWhile { it in "\"]" }
            .split("\", \"")

    @TypeConverter
    fun toString(tags: Tags): String =
        if (tags.isEmpty()) "[]"
        else tags.joinToString(separator = "\", \"", prefix = "[\"", postfix = "\"]")
}
