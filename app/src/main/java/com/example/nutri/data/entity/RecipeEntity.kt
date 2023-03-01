package com.example.nutri.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nutri.data.dto.Ingredient
import com.example.nutri.data.dto.Nutrient

@Entity
class RecipeEntity (
    @PrimaryKey
    val id: Int?,
    val url: String?,
    val name: String,
    val calories: Double,
    val dietLabel: List<String>?,
    val healthLabels: List<String>,
    val ingredients: List<Ingredient>,
    val nutrient: List<Nutrient>,
    )