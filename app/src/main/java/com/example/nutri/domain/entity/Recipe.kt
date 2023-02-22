package com.example.nutri.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nutri.domain.dto.Ingredient
import com.example.nutri.domain.dto.Nutrient

@Entity
data class Recipe (
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