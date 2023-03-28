package com.example.nutri.data.statistics.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "meal_categories",
    indices = [
        Index(value = ["id"], unique = true),
        Index(value = ["text"], unique = true)
    ]
)
class MealCategory (

    val id: String,

    @PrimaryKey
    val db_id: Int?,
    val text: String
    )