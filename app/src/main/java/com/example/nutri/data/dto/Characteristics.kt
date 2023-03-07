package com.example.nutri.data.dto

data class Characteristics (
    val quantity: Double,
    val measure: String,
    val foodMatch: String,
    val food: String,
    val foodId: String,
    val weight: Double,
    val retainedWeight: Double,
    val nutrients: TotalNutrients,
    val measureUri: String,
    val status: String
)
