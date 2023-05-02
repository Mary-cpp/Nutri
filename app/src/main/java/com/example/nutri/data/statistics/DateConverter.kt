package com.example.nutri.data.statistics

import androidx.room.TypeConverter
import com.example.nutri.ui.screens.home.dateFormat
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromDate (value: String) : Date = dateFormat.parse(value) ?: Date()

    @TypeConverter
    fun toDate (date: Date) : String = dateFormat.format(date)
}