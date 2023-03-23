package com.example.nutri.data.recipe.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipes",
    indices = [Index(value = ["id"], unique = true)])
class RecipeEntity (

    val id: String,

    @PrimaryKey(autoGenerate = true)
    val db_id: Int? = 0,

    val url: String?,
    var name: String?,
    val calories: Long?)