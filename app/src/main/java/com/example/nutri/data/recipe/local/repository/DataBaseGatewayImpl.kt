package com.example.nutri.data.recipe.local.repository

import android.util.Log
import com.example.nutri.core.ResultState
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.recipe.local.entity.*
import com.example.nutri.data.recipe.remote.dto.Characteristics
import com.example.nutri.data.recipe.remote.dto.Ingredient
import com.example.nutri.data.recipe.remote.dto.nutrients.BaseNutrient
import com.example.nutri.domain.recipes.DataBaseGateway
import com.example.nutri.domain.recipes.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
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

            recipe.totalNutrients?.let{
                database.recipeDAO().addNutrients(mapToNutrientEntity(it))
            }

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
                    mapRecipeIngredients(recipe.ingredients[0], recipeId),
                    mapToNutrientsInRecipeList(recipeId, recipe.totalNutrients!!)
                )

                database.recipeDAO().addCommonRecipe(recipeEntity)
            }
        }
        Log.d(TAG, "saveToLocal         END")

        return recipeId
    }

    override suspend fun getLocalRecipesList() : Flow<ResultState<List<Recipe>>> {
        Log.d(TAG, "getLocalRecipesList         START")

        return try {
            database.recipeDAO().getRecipes()
                .flowOn(Dispatchers.IO)
                .map {
                    ResultState.Success(value = it.map { recipeEntity -> mapToRecipe(recipeEntity) })
                }.flowOn(Dispatchers.IO)
        } catch (e: Exception) {
            Log.e(TAG, "Caught ${e.stackTrace}")
            flowOf(ResultState.Error(exception = e))
        }
    }

    override suspend fun getRecipe(recipeId: String): Flow<ResultState<Recipe>> {
        Log.d(TAG, "getRecipe    RecipeID: $recipeId    START")

        return try {
            database.recipeDAO().getCommonRecipe(recipeId)
                .flowOn(Dispatchers.IO)
                .map { commonRecipe ->
                    ResultState.Success(mapCommonEntityToRecipe(commonRecipe))
                }.flowOn(Dispatchers.IO)
        }
        catch (e: Exception) {
            Log.e(TAG, "Caught $e")
            flowOf( ResultState.Error(exception = e))
        }
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

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipe.id?.let{
            database.recipeDAO().deleteRecipe(mapToRecipeEntity(it, recipe))
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

    private suspend fun mapToNutrientsInRecipeList(
        recipeId: String,
        nutrients: Map<String, BaseNutrient>
    ) : List<NutrientsInRecipe>{

        val listOfSpecifiedNutrients = mutableListOf<NutrientsInRecipe>()

        nutrients.forEach {
            val id = database.recipeDAO().getNutrientIdByName(it.key)
            listOfSpecifiedNutrients.add(NutrientsInRecipe(recipeId, id.toInt(), it.value.quantity, it.value.unit))
        }

        return listOfSpecifiedNutrients
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
            calories = ingredient.nutrients?.get("ENERC_KCAL")!!.quantity)
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

    suspend fun mapCommonEntityToRecipe(
        recipe: RecipeEntityCommon
    ) : Recipe {

        val labels = mapLabelsEntityToLabels(recipe.labels)

        return Recipe(
            id = recipe.recipeEntity.id,
            uri = recipe.recipeEntity.url,
            totalWeight = recipe.recipeEntity.weight,
            name = recipe.recipeEntity.name,
            calories = recipe.recipeEntity.calories,
            totalNutrients = mapLocalNutrients(recipe.nutrientsInRecipe),
            healthLabels = labels[0],
            cautions = labels[1],
            dietLabels = labels[2],
            ingredients = mapIngredientEntity(recipe.ingredientsInRecipe)
        )
    }

    private suspend fun mapIngredientEntity(ingredients: List<IngredientInRecipe>) : List<Ingredient>{

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

    private suspend fun mapLocalNutrients(
        nutrients: List<NutrientsInRecipe>
    ) : Map<String, BaseNutrient> {

        val map: MutableMap<String, BaseNutrient> = mutableMapOf()

        nutrients.forEach {
            val nutrient = database.recipeDAO().getNutrientById(it.nutrientId)
            map[nutrient.name] =
                BaseNutrient()
                    .setFields(
                        label = nutrient.label,
                        quantity = it.quantity,
                        unit = it.unit
                    )
        }

        return map.toMap()
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

    private fun mapToNutrientEntity(
        map: Map<String, BaseNutrient>
    ) : List<NutrientEntity>{

        val nutrientsList = mutableListOf<NutrientEntity>()

        map.forEach {
            nutrientsList.add(NutrientEntity(label = it.value.label, name = it.key))
        }

        return nutrientsList.toList()
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