package com.example.nutri.data.entity

import com.example.nutri.data.dto.Ingredient

class RecipeEntity (
    val url: String?,
    val name: String,
    val calories: Double,
    val totalWeight: Double? = 0.0,
    val cautions: List<String?>? = null,
    val dietLabels: List<String?> = emptyList(),
    val healthLabels: List<String?>? = null,
    val ingredients: List<Ingredient?>? = null,
    )