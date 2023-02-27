package com.example.nutri.domain.dto

data class Response (
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