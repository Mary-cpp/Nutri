package com.example.nutri.data.recipe.local.repository

import android.util.Log
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.recipe.remote.dto.Characteristics
import com.example.nutri.data.recipe.remote.dto.Ingredient
import com.example.nutri.data.recipe.local.entity.*
import com.example.nutri.domain.recipes.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.StringBuilder
import java.util.UUID
import javax.inject.Inject

class DataBaseGatewayImpl @Inject constructor(
    val database: RecipeDatabase
    ) : DataBaseGateway {

    val TAG: String = "DataBaseGatewayImpl"


    override suspend fun saveToLocal(recipe: Recipe, recipeName: String): String {
        Log.d(TAG, "saveToLocal         START")

        // Creating ID for new recipe
        val recipeId = UUID.randomUUID().toString()

        withContext(Dispatchers.IO){

            // saving Labels of theRecipe
            saveLabels(recipe)

            // saving Ingredients
            recipe.ingredients!![0].parsed?.let{
                database.recipeDAO().addIngredients(
                    mapToIngredientEntity(it))
            }

            // mapping to RecipeEntity
            val recipeEnt = mapToRecipeEntity(recipeId, recipe)
            recipeEnt.name = recipeName

            // Combining entities to TransactEntity
            val recipeEntity = createRecipeEntityCommon(
                recipeEnt,
                createSpecifiedLabelList(recipeId, getAllRecipeLabels(recipe)),
                getAllRecipeIngredients(recipe.ingredients[0], recipeId)
            )

            database.recipeDAO().addCommonRecipe(recipeEntity)
        }

        Log.d(TAG, "saveToLocal         END")

        return recipeId
    }

    override suspend fun getLocalRecipesList() : List<Recipe> {

        Log.d(TAG, "getLocalRecipesList         START")

        val entityRecipes: List<RecipeEntity>

        withContext(Dispatchers.IO){

            entityRecipes = database.recipeDAO().getRecipes()
        }


        val recipes = mutableListOf<Recipe>()

        // convert to domain Recipes
        entityRecipes.forEach {
            recipes.add(mapToRecipe(it))
            Log.d(TAG, it.id)}

        Log.d(TAG, "getLocalRecipesList        ${recipes.size} END")

        return recipes.toList()
    }

    override suspend fun getRecipe(recipeId: String): Recipe {
        Log.d(TAG, "getRecipe    RecipeID: $recipeId    START")

        val recipeFromCommon: Recipe

        withContext(Dispatchers.IO){

            val recipeEnt = database.recipeDAO().getCommonRecipe(recipeId = recipeId)
            recipeFromCommon = mapCommonEntityToRecipe(recipeEnt)
        }

        Log.d(TAG, "getRecipe    RecipeID: $recipeId    END")

        return recipeFromCommon
    }

    override suspend fun getRecipesWithNameLike(name: String): List<Recipe>? {

        val recipes = mutableListOf<Recipe>()
        val recipesEnt : List<RecipeEntity>?

        withContext(Dispatchers.IO) {
            recipesEnt = database.recipeDAO().getRecipesWithNameLike(name)

            Log.d(TAG, "Recipes list size: ${recipesEnt?.size}")
        }

        return if (recipesEnt == null) {
            null
        } else {
            recipesEnt.forEach {
                recipes.add(mapToRecipe(it))
                Log.d(TAG, "recipe: ${it.name}")
            }
            recipes
        }
    }

    private fun getAllRecipeLabels(
        recipe:Recipe
    ) : MutableList<String>{
        val allLabels = mutableListOf<String>()

        recipe.healthLabels?.let {
            allLabels.addAll(it)
        }
        recipe.dietLabels.let {
            allLabels.addAll(it)
        }
        recipe.cautions?.let {
            allLabels.addAll(it)
        }

        return allLabels
    }
    private suspend fun getAllRecipeIngredients(
        ingredient: Ingredient,
        recipeId: String
    ): List<IngredientInRecipe> {

        val allIngredients = mutableListOf<IngredientInRecipe>()

        ingredient.parsed?.let {
            allIngredients
                .addAll(
                    createSpecifiedIngredients(
                        recipeId,
                        it
                    )
                )
        }

        return allIngredients
    }

    suspend fun saveSpecifiedIngredients(
        recipeId: String,
        ingredients : List<Characteristics>) : Int{

        val listOfSpecifiedIngredients: MutableList<IngredientInRecipe> = mutableListOf()

        ingredients.forEach{
            listOfSpecifiedIngredients.add(createSpecifiedIngredient(recipeId, it))
        }

        database.recipeDAO().addIngredientsOfRecipe(listOfSpecifiedIngredients)

        return listOfSpecifiedIngredients.size
    }

    private suspend fun createSpecifiedIngredients(
        recipeId: String,
        ingredients : List<Characteristics>
    ) : List<IngredientInRecipe>{

        val listOfSpecifiedIngredients: MutableList<IngredientInRecipe> = mutableListOf()

        ingredients.forEach{
            listOfSpecifiedIngredients.add(createSpecifiedIngredient(recipeId, it))
        }
        return listOfSpecifiedIngredients
    }

    private suspend fun createSpecifiedLabelList(
        recipeId: String,
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

    private suspend fun createSpecifiedIngredient(
        recipeId: String,
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

    fun mapToRecipe(recipe: RecipeEntity) : Recipe {
        return Recipe(
            id = recipe.id,
            uri = recipe.url,
            name = recipe.name,
            calories = recipe.calories
        )
    }

    private fun mapToRecipeEntity(
        uuid: String,
        recipe: Recipe
    ) : RecipeEntity {
        return RecipeEntity(
            db_id = null,
            id = uuid,
            name = null,
            url = recipe.uri,
            calories = recipe.calories
        )
    }

    private suspend fun mapCommonEntityToRecipe(
        recipe: RecipeEntityCommon
    ) : Recipe {

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

    private fun createRecipeEntityCommon(
        recipeEntity: RecipeEntity,
        labels: List<LabelsInRecipe>,
        ingredients: List<IngredientInRecipe>
    ) : RecipeEntityCommon{

        return RecipeEntityCommon(recipeEntity, labels, ingredients)
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