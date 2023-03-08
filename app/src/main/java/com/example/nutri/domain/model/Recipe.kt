package com.example.nutri.domain.model

import com.example.nutri.data.dto.Ingredient
import com.example.nutri.data.dto.TotalNutrients
import com.example.nutri.data.dto.nutrients.TotalNutrientsKCal

data class Recipe (
    var id: Int? = null,
    val uri: String? = "",
    val name: String? = "",
    val calories: Long? = 0L,
    val totalWeight: Double? = 0.0,
    val dietLabels: List<String> = emptyList(),
    val healthLabels: List<String>? = null,
    val cautions: List<String>? = null,
    val totalNutrients: TotalNutrients? = null,
    val totalDaily: TotalNutrients? = null,
    val ingredients: List<Ingredient>? = null,
    val totalNutrientsKCal: TotalNutrientsKCal? = null
    ) {

    companion object{
        fun makeRecipe() : Recipe{
            return Recipe(
                id = 1011101010,
                uri = "some uri",
                name = "Recipe Name",
                calories = 101100,
                totalWeight = 0.0,
                dietLabels = listOf("label1", "label2", "label3"),
                healthLabels = listOf("label1", "label2", "label3"),
                cautions = listOf("caution1", "caution2", "caution3"),
                ingredients = listOf(Ingredient("list of parsed ingredients", null))
            )
        }
    }
}