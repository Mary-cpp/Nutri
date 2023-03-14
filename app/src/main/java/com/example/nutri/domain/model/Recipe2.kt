package com.example.nutri.domain.model

import com.example.nutri.data.dto.Characteristics
import com.example.nutri.data.dto.Ingredient
import com.example.nutri.data.dto.TotalNutrients
import com.example.nutri.data.dto.nutrients.Nutrient
import com.example.nutri.data.dto.nutrients.TotalNutrientsKCal

class Recipe2 (
    var id: Int? = null,
    val name: String? = null,
    val calories: Long? = 0L,
    val totalWeight: Double? = 0.0,
    val dietLabels: List<String> = emptyList(),
    val healthLabels: List<String> = emptyList(),
    val cautions: List<String>? = null,
    val totalNutrients: List<Nutrient>? = null,
    val totalDaily: List<Nutrient>? = null,
    val parsedIngredientString: String? = null,
    val ingredients: List<Characteristics>? = null,
    val totalNutrientsKCal: TotalNutrientsKCal? = null
) {
    companion object{
        fun makeRecipe() : Recipe2{
            return Recipe2(
                id = 1011101010,
                name = "Recipe Name",
                calories = 101100,
                totalWeight = 0.0,
                dietLabels = listOf("label1", "label2", "label3"),
                healthLabels = listOf("label1", "label2", "label3"),
                cautions = listOf("caution1", "caution2", "caution3"),
                ingredients = listOf(Characteristics.makeCharacteristics(), Characteristics.makeCharacteristics())
            )
        }
    }
}