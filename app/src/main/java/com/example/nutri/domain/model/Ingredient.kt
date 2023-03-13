package com.example.nutri.domain.model

class Ingredient (
    val ingredientName : String,
    val ingredientAmount: Int,
    val ingredientUnits: String
){

    companion object{
        fun emptyIngredient() = Ingredient(
            ingredientName = "",
            ingredientAmount = 0,
            ingredientUnits = "g"
        )
    }
}