package com.example.myapplication.products.data

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class LocalDateTimeConverter {
    @TypeConverter
    fun toDateTime(millis: Long): LocalDateTime =
        Instant.fromEpochMilliseconds(millis).toLocalDateTime(TimeZone.currentSystemDefault())

    @TypeConverter
    fun toMillis(localDateTime: LocalDateTime): Long =
        localDateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()

}