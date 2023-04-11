package com.example.nutri.data.recipe.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrients")
class NutrientEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val label: String,
    val name: String,
)