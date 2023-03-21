package com.example.nutri.data.recipe.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    val name: String)