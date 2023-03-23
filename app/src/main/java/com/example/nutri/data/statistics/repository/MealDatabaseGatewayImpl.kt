package com.example.nutri.data.statistics.repository

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

    override suspend fun getMealsList(): List<Meal> {
        val commonMeals = database.mealDAO().getCommonMeals()

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
    }
}