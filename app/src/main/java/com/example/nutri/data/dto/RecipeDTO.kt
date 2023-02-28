package com.example.nutri.data.dto

data class RecipeDTO (
    val uri: String,
    val calories: Long?,
    val totalWeight: Double?,
    val dietLabels: List<String?>?,
    val healthLabels: List<String?>,
    val cautions: List<String?>?,
    val totalNutrients: Nutrient?,
    val totalDaily: Nutrient?,
    val ingredients: List<Ingredient?>,
    val totalNutrientsKCal: Nutrient?
)