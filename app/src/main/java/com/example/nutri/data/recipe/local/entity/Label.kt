package com.example.nutri.data.recipe.local.entity

import androidx.room.*

@Entity(
    tableName = "Label",
)
class Label (

    @PrimaryKey(autoGenerate = true)
    val id:Int?,

    val text: String,
    val category: String,
)
