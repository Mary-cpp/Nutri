package com.example.nutri.domain.gateway

import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.database.dao.RecipeDAO
import com.example.nutri.data.recipe.local.repository.DataBaseGatewayImpl
import com.example.nutri.domain.recipes.model.Recipe
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito

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
    @ValueSource(ints = [1, 4, 50, 100])
    fun checkIfLabelsMappedCorrectly(argument: Int) = runBlocking {
        Mockito.`when`(dao.addLabels(listOf())).thenReturn(Unit)

        val expected = argument*3
        val actual = DataBaseGatewayImpl(database)
            .mapLabels(createRecipeWithLabels(argument))

        assertEquals(expected, actual)
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
}