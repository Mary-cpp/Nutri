package com.example.nutri.data.statistics.repository

import android.util.Log
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.recipe.local.repository.DataBaseGatewayImpl
import com.example.nutri.data.statistics.entities.MealCategory
import com.example.nutri.data.statistics.entities.MealCommonEntity
import com.example.nutri.data.statistics.entities.MealEntity
import com.example.nutri.data.statistics.entities.RecipeInMeal
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.domain.statistics.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class MealDatabaseGatewayImpl(
    val database: RecipeDatabase
) : MealDataBaseGateway {

    private val tag = "MealDatabaseGatewayImpl"
    override suspend fun saveMeal(meal: Meal): Int {

        val mealUId = UUID.randomUUID().toString()
        val mealCategoryUId = UUID.randomUUID().toString()

        val mealCategory = MealCategory(
            id = mealCategoryUId,
            db_id = null,
            text = meal.name
        )
        val mealEntity = MealEntity(
            id = mealUId,
            db_id = null,
            idCategory = mealCategoryUId,
            date = meal.date
        )

        val recipesInMeal = mutableListOf<RecipeInMeal>()

        withContext(Dispatchers.IO){
        meal.recipes.forEach {
            it.name?.let { name ->
                recipesInMeal.add(
                    RecipeInMeal(
                        idMeal = mealUId,
                        idRecipe = database.recipeDAO().getRecipeId(name)
                    )
                )
            }
        }}

        return withContext(Dispatchers.IO) {
            database
                .mealDAO()
                .addCommonMeal(
                    MealCommonEntity(
                        mealEntity,
                        mealCategory,
                        recipesInMeal
                    )
                ).toInt()
        }
    }

    override suspend fun addRecipeInMeal(meal: Meal){

        val category = database.mealDAO().getCategoryIdByName(meal.name)
        val dbMealId = database
            .mealDAO()
            .getMealByDateAndCategory(
                date = meal.date,
                idCategory = category
            )

        if (dbMealId == null){
            saveMeal(meal)
        }
        else{
            val recipesInMeal = mutableListOf<RecipeInMeal>()

            withContext(Dispatchers.IO){
                meal.recipes.forEach {
                    it.name?.let { name ->
                        recipesInMeal.add(
                            RecipeInMeal(
                                idMeal = dbMealId,
                                idRecipe = database.recipeDAO().getRecipeId(name)
                            )
                        )
                    }
                }}

            database
                .mealDAO()
                .addRecipesInMeal(recipesInMeal)
        }
    }

    override suspend fun saveMealList(list: List<Meal>) {

        list.forEach {
            saveMeal(it)
        }
    }

    override suspend fun getMealsList(date: Date?): List<Meal> {

        Log.d(tag, "getMealsList START")
        var commonMeals = database.mealDAO().getCommonMeals()

        date?.let{
            Log.d(tag, "Date: ${it.time}")

            commonMeals = database.mealDAO().getCommonMealsByDate(it)
        }

        val mealsList = mutableListOf<Meal>()

        withContext(Dispatchers.IO){

            commonMeals.forEach {

                val recipes = mutableListOf<Recipe>()

                it.recipes.forEach { recipeInMeal ->
                    recipes.add(
                        DataBaseGatewayImpl(
                            database
                        ).mapToRecipe(
                            database
                                .recipeDAO()
                                .getRecipeById(recipeInMeal.idRecipe)
                        )
                    )
                }

                mealsList.add(Meal(
                    name = it.mealCategory.text,
                    date = it.meal.date,
                    recipes = recipes
                ))
            }
        }
        Log.d(tag, "Meal listSize = ${mealsList.size}")
        Log.d(tag, "getMealsList END")

        return mealsList.toList()
    }

/*    override suspend fun getTodayMeals(date: Date?): List<Meal> {


        var commonMeals = database.mealDAO().getCommonMeals()

        date?.let{
            commonMeals = database.mealDAO().getCommonMealsByDate(date)
        }

        val mealsList = mutableListOf<Meal>()

        withContext(Dispatchers.IO){

            commonMeals.forEach {

                val recipes = mutableListOf<Recipe>()

                it.recipes.forEach { recipeInMeal ->
                    recipes.add(
                        DataBaseGatewayImpl(
                            database
                        ).mapToRecipe(
                            database
                                .recipeDAO()
                                .getRecipeById(recipeInMeal.idRecipe)
                        )
                    )
                }

                mealsList.add(Meal(
                    name = it.mealCategory.text,
                    date = it.meal.date,
                    recipes = recipes
                ))
            }
        }

        return mealsList.toList()
    }*/
}