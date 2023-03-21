package com.example.nutri.data.recipe.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
class RecipeEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val url: String?,
    var name: String?,
    val calories: Long?)