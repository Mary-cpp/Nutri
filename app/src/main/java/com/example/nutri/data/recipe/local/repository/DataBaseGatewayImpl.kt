package com.example.nutri.data.recipe.local.repository

import android.util.Log
import com.example.nutri.data.recipe.local.database.RecipeDatabase
import com.example.nutri.data.recipe.remote.dto.Characteristics
import com.example.nutri.data.recipe.remote.dto.Ingredient
import com.example.nutri.data.recipe.local.entity.*
import com.example.nutri.domain.recipes.model.Recipe
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
        saveLabels(recipe)

        database.recipeDAO().addLabelsForRecipe(
            createSpecifiedLabelList(
                savedRecipeId,
                recipe.healthLabels!!))
        database.recipeDAO().addLabelsForRecipe(
            createSpecifiedLabelList(
                savedRecipeId,
                recipe.dietLabels))
        database.recipeDAO().addLabelsForRecipe(
            createSpecifiedLabelList(
                savedRecipeId,
                recipe.cautions!!))

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
            recipes.add(mapToRecipe(it))
            Log.d(TAG, it.id.toString())}

        Log.d(TAG, "getLocalRecipesList        ${recipes.size} END")

        return recipes.toList()
    }

    override suspend fun getRecipe(recipeId: Int): Recipe {
        Log.d(TAG, "getRecipe    RecipeID: $recipeId    START")


        val recipeEnt = database.recipeDAO().getRecipeById(recipeId = recipeId)

        val recipeFromCommon = mapCommonEntityToRecipe(recipeEnt)

        Log.d(TAG, "getRecipe    RecipeID: $recipeId    END")

        return recipeFromCommon
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

    suspend fun createSpecifiedLabelList(
        recipeId: Int,
        labels: List<String>
    ) : List<LabelsInRecipe> {
        val listOfLabels : MutableList<LabelsInRecipe> = mutableListOf()

        labels.forEach {
            listOfLabels.add(
                LabelsInRecipe(idRecipe = recipeId,
                idLabel = database.recipeDAO().getLabelId(it))
            )
        }

        return listOfLabels.toList()
    }

    suspend fun createSpecifiedIngredient(
        recipeId: Int,
        ingredient: Characteristics
    )
            = IngredientInRecipe(
        idRecipe =  recipeId,
        idIngredient = database.recipeDAO().getIngredientId(ingredient.foodMatch),
        amount = ingredient.quantity,
        units = ingredient.measure,
        calories = ingredient.nutrients!!.ENERCKCAL!!.quantity
    )

    suspend fun saveLabels(
        recipe: Recipe
    ) : Int{
        Log.d(TAG, "Inserting recipe labels         START")

        val dietLabels = mapLabelsToEntity(recipe.dietLabels,  "dietLabels")
        val healthLabels = mapLabelsToEntity(recipe.healthLabels!!,  "healthLabels")
        val cautions = mapLabelsToEntity(recipe.cautions!!,  "cautions")

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
            name = null,
            url = recipe.uri,
            calories = recipe.calories
        )
    }

    private suspend fun mapCommonEntityToRecipe(
        recipe: RecipeEntity
    ) : Recipe {

        val entityLabels = database.recipeDAO().getRecipeWithLabels(recipe.id)
        val entityRecipeIngredients = database.recipeDAO().getRecipeIngredients(recipe.id)

        val labels = mapLabelsEntityToLabels(entityLabels)

        return Recipe(
            id = recipe.id,
            uri = recipe.url,
            name = recipe.name,
            calories = recipe.calories,
            healthLabels = labels[0],
            cautions = labels[1],
            dietLabels = labels[2],
            ingredients = mapLocalIngredients(entityRecipeIngredients)
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

    private suspend fun mapLabelsEntityToLabels(labels : List<LabelsInRecipe>) : List<List<String>>{
        val healthLabels: MutableList<String> = mutableListOf()
        val cautions: MutableList<String> = mutableListOf()
        val dietLabels: MutableList<String> = mutableListOf()

        labels.forEach{

            val label = database.recipeDAO().getLabelById(it.idLabel)

            if (label.category == "dietLabels"){ dietLabels.add(label.text)}
            if (label.category == "healthLabels"){ healthLabels.add(label.text)}
            if (label.category == "cautions"){ cautions.add(label.text)}
        }
        return listOf(healthLabels.toList(), cautions.toList(), dietLabels.toList())
    }

    private fun mapToIngredientEntity(
        list: List<Characteristics>
    ): List<IngredientEntity> {

        val ingredientsList: MutableList<IngredientEntity> = mutableListOf()

        list.forEach{
            ingredientsList.add(
                IngredientEntity(id = null,
                name = it.foodMatch)
            )
        }
        return ingredientsList.toList()
    }

    private fun mapLabelsToEntity(labels:  List<String>,  category: String) : List<Label>{

        val entityLabels : MutableList<Label> = mutableListOf()

        labels.forEach{
            entityLabels.add(
                Label(id = null,
                text = it,
                category = category
            )
            )
        }
        return entityLabels.toList()
    }
}