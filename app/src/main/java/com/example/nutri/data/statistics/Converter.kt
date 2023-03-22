package com.example.nutri.data.statistics

import androidx.room.TypeConverter
import java.util.*

class Converter {

    @TypeConverter
    fun fromTimestamp (value: Long?) : Date? = value?.let { Date(it) }

    @TypeConverter
    fun toTimestamp (date: Date?) : Long? = date?.time?.toLong()
}