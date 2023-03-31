package com.example.nutri.data.recipe.remote.dto


data class Ingredient(
    val text: String,
    val parsed: List<Characteristics>?
) {

    companion object{
        fun Ingredient.mapToDomainIngredients()
        : List<com.example.nutri.domain.recipes.model.Ingredient>{
            val listOfIngredients = mutableListOf<com.example.nutri.domain.recipes.model.Ingredient>()
            parsed?.forEach {
                listOfIngredients.add(com.example.nutri.domain.recipes.model.Ingredient(
                    ingredientName = it.foodMatch,
                    ingredientAmount = it.quantity.toInt(),
                    ingredientUnits = it.measure,
                ))
            }
            return listOfIngredients
        }
    }
}
