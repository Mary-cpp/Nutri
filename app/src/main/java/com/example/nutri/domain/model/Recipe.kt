package com.example.nutri.domain.model

import com.example.nutri.data.dto.Ingredient
import com.example.nutri.data.dto.Nutrient
import com.example.nutri.data.dto.NutrientList

data class Recipe (
    val uri: String? = "",
    val calories: Long? = 0L,
    val totalWeight: Double? = 0.0,
    val dietLabels: List<String?> = emptyList(),
    val healthLabels: List<String?>? = null,
    val cautions: List<String?>? = null,
    val totalNutrients: NutrientList? = null,
    val totalDaily: Nutrient? = null,
    val ingredients: List<Ingredient?>? = null,
    val totalNutrientsKCal: Nutrient? = null
    ) {
}