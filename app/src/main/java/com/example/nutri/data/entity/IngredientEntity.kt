package com.example.nutri.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class IngredientEntity(
    @PrimaryKey()
    val id: Int,
     val name: String)