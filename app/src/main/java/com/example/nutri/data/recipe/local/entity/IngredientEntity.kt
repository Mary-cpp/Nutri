package com.example.nutri.data.recipe.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients",
indices = [Index(value=["name"], unique = true)])
class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String)