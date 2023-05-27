package com.example.nutri.data.statistics

import androidx.room.TypeConverter
import com.example.nutri.ui.screens.home.dateFormat
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate (value: String) : Date = dateFormat.parse(value) ?: Date()

    @TypeConverter
    fun fromDate (date: Date) : String = dateFormat.format(date)
}