package com.example.nutri.domain.model

class Ingredient (
    val ingredientName : String,
    val ingredientAmount: Double,
    val ingredientUnits: String
){

    companion object{
        fun emptyIngredient() = Ingredient(
            ingredientName = "",
            ingredientAmount = 0.0,
            ingredientUnits = "g"
        )
    }
}