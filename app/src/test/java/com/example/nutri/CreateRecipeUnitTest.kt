package com.example.nutri

import com.example.nutri.domain.model.Ingredient
import org.junit.Assert
import org.junit.Test

class CreateRecipeUnitTest {

    @Test
    fun ingredient_to_string_isCorrect(){

        val ingredient = Ingredient(ingredientName = "milk", ingredientAmount = 200, ingredientUnits = "g")

        val message = "Ingredient.ToString() result does not match a template "
        val expected = "milk 200g"
        val actual = ingredient.toString()

        Assert.assertEquals(
            message,
            expected,
            actual
        )
    }
}