package com.example.nutri.domain.dto

data class Characteristics (
    val quantity: Double,
    val measure: String,
    val foodMatch: String,
    val food: String,
    val foodId: String,
    val weight: Double,
    val retainedWeight: Double,
    val nutrients: Nutrient,
    val measureUri: String,
    val status: String
) {
}
