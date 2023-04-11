package com.example.nutri.domain.recipes.model

import com.example.nutri.data.recipe.remote.dto.Characteristics
import com.example.nutri.data.recipe.remote.dto.Ingredient
import com.example.nutri.data.recipe.remote.dto.nutrients.BaseNutrient
import com.google.gson.annotations.SerializedName

data class Recipe (
    var id: String? = null,
    val uri: String? = "",
    val name: String? = "",
    val calories: Long? = 0L,
    val totalWeight: Double? = 0.0,
    val dietLabels: List<String> = emptyList(),
    val healthLabels: List<String>? = null,
    val cautions: List<String>? = null,
    @SerializedName("totalNutrients")
    val totalNutrients: Map<String, BaseNutrient>? = null,
    val totalDaily: Map<String, BaseNutrient>? = null,
    val ingredients: List<Ingredient>? = null,
    @SerializedName("totalNutrientsKCal")
    val totalNutrientsKCal: Map<String, BaseNutrient>? = null
) {

    companion object{
        fun makeRecipe() : Recipe {
            return Recipe(
                id = "1011101010",
                uri = "some uri",
                name = "Recipe Name",
                calories = 101100,
                totalWeight = 0.0,
                dietLabels = listOf("label1", "label2", "label3"),
                healthLabels = listOf("label1", "label2", "label3"),
                cautions = listOf("caution1", "caution2", "caution3"),
                ingredients = listOf(Ingredient("list of parsed ingredients", listOf(Characteristics.makeCharacteristics(), Characteristics.makeCharacteristics())))
            )
        }
    }
}

