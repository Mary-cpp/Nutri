package com.example.nutri.data.repository

import android.util.Log
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.dto.Characteristics
import com.example.nutri.data.dto.Ingredient
import com.example.nutri.data.entity.*
import com.example.nutri.domain.model.Recipe
import java.lang.StringBuilder
import javax.inject.Inject

class DataBaseGatewayImpl @Inject constructor(
    val database: RecipeDatabase
    ) : DataBaseGateway {

    val TAG: String = "DataBaseGatewayImpl"


    override suspend fun saveToLocal(recipe: Recipe, recipeName: String): Int {
        Log.d(TAG, "saveToLocal         START")

        // saving RecipeEntity
        val recipeEnt = mapToRecipeEntity(recipe)
        recipeEnt.name = recipeName
        database.recipeDAO().add(recipeEnt)

        // receiving saved recipeId
        val savedRecipeId = database.recipeDAO().getRecipeId(recipeName)

        // saving Labels of theRecipe
        saveLabels(recipe, savedRecipeId)

        // saving Ingredients
        database.recipeDAO().addIngredients(
                mapToIngredientEntity(recipe.ingredients!![0].parsed!!))

        // saving Ingredients with the characteristics for the current Recipe
        saveSpecifiedIngredients(savedRecipeId, recipe.ingredients[0].parsed!!)

        Log.d(TAG, "saveToLocal         END")

        return savedRecipeId
    }

    override suspend fun getLocalRecipesList() : List<Recipe> {

        Log.d(TAG, "getLocalRecipesList         START")

        val entityRecipes = database.recipeDAO().getRecipes()
        val recipes = mutableListOf<Recipe>()
        entityRecipes.forEach {
            recipes.add(mapToRecipe(it)) }



        Log.d(TAG, "getLocalRecipesList        ${recipes.size} END")

        return recipes.toList()
    }

    override suspend fun getRecipe(recipeId: Int): Recipe {
        Log.d(TAG, "getLocalRecipesList         START")

        val recipe = database.recipeDAO().getRecipeById(recipeId)

        Log.d(TAG, " Recipe : $recipe")

        Log.d(TAG, "getLocalRecipesList        ${recipe.recipeEntity.name} END")

        return mapCommonEntityToRecipe(recipe)
    }

    suspend fun saveSpecifiedIngredients(recipeId: Int,
    ingredients : List<Characteristics>) : Int{

        val listOfSpecifiedIngredients: MutableList<IngredientInRecipe> = mutableListOf()

        ingredients.forEach{
            listOfSpecifiedIngredients.add(createSpecifiedIngredient(recipeId, it))
        }

        database.recipeDAO().addIngredientsOfRecipe(listOfSpecifiedIngredients)

        return listOfSpecifiedIngredients.size
    }

    suspend fun createSpecifiedIngredient(
        recipeId: Int,
        ingredient: Characteristics)
    = IngredientInRecipe(
            idRecipe =  recipeId,
            idIngredient = database.recipeDAO().getIngredientId(ingredient.foodMatch),
            amount = ingredient.quantity,
            units = ingredient.measure,
            calories = ingredient.nutrients!!.ENERCKCAL!!.quantity
    )

    suspend fun saveLabels(
        recipe: Recipe,
        id: Int
    ) : Int{
        Log.d(TAG, "Inserting recipe labels         START")

        val dietLabels = mapLabelsToEntity(recipe.dietLabels, id, "dietLabels")
        val healthLabels = mapLabelsToEntity(recipe.healthLabels!!, id, "healthLabels")
        val cautions = mapLabelsToEntity(recipe.cautions!!, id, "cautions")

        database.recipeDAO().addLabels(dietLabels)
        database.recipeDAO().addLabels(healthLabels)
        database.recipeDAO().addLabels(cautions)

        Log.d(TAG, "Inserting recipe labels         END")

        return dietLabels.size + healthLabels.size + cautions.size
    }

    private fun mapToRecipe(recipe: RecipeEntity) : Recipe {
        return Recipe(
            id = recipe.id,
            uri = recipe.url,
            name = recipe.name,
            calories = recipe.calories
       )
    }

    private fun mapToRecipeEntity(
        recipe: Recipe
    ) : RecipeEntity {
        return RecipeEntity(
            id = null,
            name = null,
            url = recipe.uri,
            calories = recipe.calories
       )
   }

    private suspend fun mapCommonEntityToRecipe(
        recipe: RecipeEntityCommon
    ) : Recipe{

        val labels = mapLabelsEntityToLabels(recipe.labels)

        return Recipe(
            id = recipe.recipeEntity.id,
            uri = recipe.recipeEntity.url,
            name = recipe.recipeEntity.name,
            calories = recipe.recipeEntity.calories,
            healthLabels = labels[0],
            cautions = labels[1],
            dietLabels = labels[2],
            ingredients = mapLocalIngredients(recipe.ingredientsInRecipe)
        )
    }

    private suspend fun mapLocalIngredients( ingredients: List<IngredientInRecipe>) : List<Ingredient>{

        val listOfIngredients : MutableList<Characteristics> = mutableListOf()
        val text = StringBuilder()
        ingredients.forEach {

            val ingredientName = database.recipeDAO().getIngredientById(it.idIngredient).name
            text.append("$ingredientName ${it.amount}${it.units}")

            listOfIngredients.add(
                Characteristics(
                    foodMatch = ingredientName,
                    quantity = it.amount,
                    measure = it.units,
                )
            )
        }
        return listOf(Ingredient(text = text.toString(), parsed = listOfIngredients))
    }

    private fun mapLabelsEntityToLabels(labels : List<Label>) : List<List<String>>{
        val healthLabels: MutableList<String> = mutableListOf()
        val cautions: MutableList<String> = mutableListOf()
        val dietLabels: MutableList<String> = mutableListOf()

        labels.forEach{
            if (it.category == "dietLabels"){ dietLabels.add(it.text)}
            if (it.category == "healthLabels"){ healthLabels.add(it.text)}
            if (it.category == "cautions"){ cautions.add(it.text)}
        }
        return listOf(healthLabels.toList(), cautions.toList(), dietLabels.toList())
    }

    private fun mapToIngredientEntity(
        list: List<Characteristics>
    ): List<IngredientEntity> {

            val ingredientsList: MutableList<IngredientEntity> = mutableListOf()

            list.forEach{
                ingredientsList.add(IngredientEntity(id = null,
                    name = it.foodMatch))
            }

        return ingredientsList.toList()
    }

    private fun mapLabelsToEntity(labels:  List<String>, recipeId: Int, category: String) : List<Label>{

        val entityLabels : MutableList<Label> = mutableListOf()

        labels.forEach{
            entityLabels.add(Label(id = null,
                recipeId = recipeId,
                text = it,
                category = category
            ))
        }

        return entityLabels.toList()
    }
}