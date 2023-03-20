package com.example.nutri.domain.recipes.model

class Ingredient (
    val ingredientName : String,
    val ingredientAmount: Int,
    val ingredientUnits: String
){

    override fun toString(): String {
        return "$ingredientName ${ingredientAmount}${ingredientUnits}"
    }
}