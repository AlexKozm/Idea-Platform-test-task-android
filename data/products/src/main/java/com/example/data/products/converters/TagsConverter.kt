package com.example.data.products.converters

import androidx.room.TypeConverter
import com.example.data.products.data.Tags


//@ProvidedTypeConverter
class TagsConverter {
    @TypeConverter
    fun toTags(string: String): Tags =
        if (string == "[]") emptyList()
        else string
            .dropWhile { it in "[\"" }
            .dropLastWhile { it in "\"]" }
            .split("\", \"")

    @TypeConverter
    fun toString(tags: Tags): String =
        if (tags.isEmpty()) "[]"
        else tags.joinToString(separator = "\", \"", prefix = "[\"", postfix = "\"]")
}
