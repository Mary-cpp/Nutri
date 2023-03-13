package com.example.nutri

import com.example.nutri.domain.model.Ingredient
import org.junit.Assert.assertArrayEquals
import org.junit.Test

class CreateRecipeUnitTest {

    @Test
    fun ingredient_to_string_isCorrect(){

        val ingredient = Ingredient(ingredientName = "milk", ingredientAmount = 200, ingredientUnits = "g")

        assertArrayEquals(
            "Ingredient.ToString is not working properly",
            "milk 200g".toCharArray(),
            ingredient.toString().toCharArray()
        )
    }
}