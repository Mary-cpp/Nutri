package com.example.nutri.data.statistics.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_categories")
class MealCategory (

    @PrimaryKey
    val id:Int?,
    val text: String
    )