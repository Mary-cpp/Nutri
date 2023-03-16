package com.example.nutri.domain.gateway

import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.database.dao.RecipeDAO
import com.example.nutri.data.dto.Characteristics
import com.example.nutri.domain.model.Recipe
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito
import java.util.*

internal class DataBaseGatewayImplTest {

    private val database = Mockito.mock(RecipeDatabase::class.java)
    private val dao = Mockito.mock(RecipeDAO::class.java)


    @BeforeEach
    internal fun setUp() {

        Mockito.`when`(database.recipeDAO()).thenReturn(dao)
    }

    @AfterEach
    internal fun tearDown() {
        database.close()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 24, 5])
    fun saveSpecifiedIngredients(argument: Int) = runBlocking {

        Mockito.`when`(dao.addIngredientsOfRecipe(emptyList())).thenReturn(Unit)

        val expected = argument

        val actual = DataBaseGatewayImpl(database)
            .saveSpecifiedIngredients(
                Random().nextInt(),
                makeListOfIngredients(argument))

        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 4, 50, 100])
    fun saveLabels(argument: Int) = runBlocking {
        Mockito.`when`(dao.addLabels(listOf())).thenReturn(Unit)

        val expected = argument*3
        val actual = DataBaseGatewayImpl(database)
            .saveLabels(createRecipeWithLabels(argument), Random().nextInt(10))

        assertEquals(expected, actual)
    }

    private fun makeListOfIngredients(size : Int) : List<Characteristics>{

        val list : MutableList<Characteristics> = mutableListOf()

        repeat(size){
            list.add(createRandomIngredient())
        }
        return list.toList()
    }


    private fun createRecipeWithLabels(size: Int)
    = Recipe(dietLabels = createLabels(size),
    healthLabels = createLabels(size),
        cautions = createLabels(size))


    private fun createLabels(size: Int) : List<String>{

        val list : MutableList<String> = mutableListOf()

        repeat(size){
            list.add("LABEL")
        }
        return list.toList()
    }

    private fun createRandomIngredient() : Characteristics{
        return Characteristics(
            quantity = Random().nextDouble(),
            measure = "MEASURE",
            foodMatch = "FOOD MATCH",
            food = "FOOD",
            foodId = "FOODiD",
            weight = Random().nextDouble(),
            retainedWeight =  Random().nextDouble(),
            nutrients = null,
            measureUri = "MEASURE URI",
            status = "STATUS"
        )
    }
}