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
import kotlin.math.roundToInt

class DataBaseGatewayImpl @Inject constructor(
    val database: RecipeDatabase
    ) : DataBaseGateway {

    val TAG: String = "DataBaseGatewayImpl"


    override suspend fun saveToLocal(recipe: Recipe, recipeName: String): String {
        Log.d(TAG, "saveToLocal         START")

        // Creating ID for new recipe
        val recipeId = recipe.id ?: UUID.randomUUID().toString()

        withContext(Dispatchers.IO){

            //saving labels
            database.recipeDAO().addLabels(mapLabels(recipe))


            // mapping to RecipeEntity
            val recipeEnt = mapToRecipeEntity(recipeId, recipe)
            recipeEnt.name = recipeName

            recipe.ingredients?.get(0)?.parsed?.let{

                //saving ingredients
                database.recipeDAO().addIngredients(mapToIngredientEntity(it))

                // Combining entities to TransactEntity
                val recipeEntity = RecipeEntityCommon(
                    recipeEnt,
                    createSpecifiedLabelList(recipeId, getAllRecipeLabels(recipe)),
                    mapRecipeIngredients(recipe.ingredients[0], recipeId)
                )

                database.recipeDAO().addCommonRecipe(recipeEntity)
            }
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

        // convert to domain Recipes
        val recipes = mutableListOf<Recipe>()

        entityRecipes.forEach {
            recipes.add(mapToRecipe(it))
        }

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
        recipe: Recipe
    ): MutableList<String> {
        val allLabels = mutableListOf<String>()

        recipe.healthLabels?.let { allLabels.addAll(it) }
        recipe.dietLabels.let { allLabels.addAll(it) }
        recipe.cautions?.let { allLabels.addAll(it) }

        return allLabels
    }

    private suspend fun mapRecipeIngredients(
        ingredient: Ingredient,
        recipeId: String
    ): List<IngredientInRecipe> {

        val allIngredients = mutableListOf<IngredientInRecipe>()

        ingredient.parsed?.let {
            allIngredients
                .addAll(
                    mapToIngredientInRecipeList(
                        recipeId,
                        it
                    )
                )
        }

        return allIngredients
    }

    private suspend fun mapToIngredientInRecipeList(
        recipeId: String,
        ingredients : List<Characteristics>
    ) : List<IngredientInRecipe>{

        val listOfSpecifiedIngredients: MutableList<IngredientInRecipe> = mutableListOf()

        ingredients.forEach{
            listOfSpecifiedIngredients.add(mapToIngredientInRecipe(recipeId, it))
        }
        return listOfSpecifiedIngredients
    }

    private suspend fun createSpecifiedLabelList(
        recipeId: String,
        labels: List<String>
    ) : List<LabelsInRecipe> {
        val listOfLabels : MutableList<LabelsInRecipe> = mutableListOf()

        labels.forEach {
            Log.w(TAG, "Label name: $it")
            listOfLabels.add(
                LabelsInRecipe(idRecipe = recipeId,
                idLabel = database.recipeDAO().getLabelId(it))
            )
        }

        return listOfLabels.toList()
    }

    private suspend fun mapToIngredientInRecipe(
        recipeId: String,
        ingredient: Characteristics
    ) : IngredientInRecipe{

        Log.d(TAG, ingredient.quantity.toString())

        return IngredientInRecipe(
            idRecipe =  recipeId,
            idIngredient = database.recipeDAO().getIngredientId(ingredient.foodMatch),
            amount = ingredient.quantity,
            units = ingredient.measure,
            calories = ingredient.nutrients?.ENERCKCAL!!.quantity)
    }

    fun mapLabels(
        recipe: Recipe
    ) : List<Label>{
        Log.d(TAG, "Inserting recipe labels         START")

        val mappedLabels = mutableListOf<Label>()

        mappedLabels.addAll(mapLabelsToEntity(recipe.dietLabels,  "dietLabels"))
        mappedLabels.addAll(mapLabelsToEntity(recipe.healthLabels!!,  "healthLabels"))
        mappedLabels.addAll(mapLabelsToEntity(recipe.cautions!!,  "cautions"))

        Log.d(TAG, "Inserting recipe labels         END")

        return mappedLabels
    }

    fun mapToRecipe(recipe: RecipeEntity) : Recipe {
        return Recipe(
            id = recipe.id,
            uri = recipe.url,
            name = recipe.name,
            totalWeight = recipe.weight,
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
            weight = recipe.totalWeight?.roundToInt()?.toDouble(),
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
            totalWeight = recipe.recipeEntity.weight,
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

    private fun mapLabelsToEntity(labels:  List<String>,  category: String) : List<Label>{

        val entityLabels : MutableList<Label> = mutableListOf()

        labels.forEach{
            entityLabels.add(
                Label(
                    id = null,
                    text = it,
                    category = category
                )
            )
        }
        return entityLabels.toList()
    }
}