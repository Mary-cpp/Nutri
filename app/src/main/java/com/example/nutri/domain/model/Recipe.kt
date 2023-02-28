package com.example.nutri.domain.model

import com.example.nutri.data.dto.Ingredient
import com.example.nutri.data.dto.Nutrient
import com.example.nutri.data.dto.NutrientList

data class Recipe (
    val uri: String,
    val calories: Long?,
    val totalWeight: Double?,
    val dietLabels: List<String?>?,
    val healthLabels: List<String?>,
    val cautions: List<String?>?,
    val totalNutrients: NutrientList,
    val totalDaily: Nutrient?,
    val ingredients: List<Ingredient?>,
    val totalNutrientsKCal: Nutrient?
)