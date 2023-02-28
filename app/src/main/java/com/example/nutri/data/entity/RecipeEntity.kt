package com.example.nutri.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nutri.data.dto.Ingredient
import com.example.nutri.data.dto.Nutrient

@Entity
data class RecipeEntity (
    @PrimaryKey
    val id: Int?,
    val name: String,
    val calories: Double,
    val dietLabel: String,
    val healthLabels: List<String>,
    val ingredients: List<Ingredient>,
    val nutrient: MutableList<Nutrient>,

    ) {
}