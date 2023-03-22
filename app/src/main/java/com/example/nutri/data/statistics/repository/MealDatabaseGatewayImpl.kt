package com.example.nutri.data.statistics.repository

import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.statistics.entities.MealCategory
import com.example.nutri.data.statistics.entities.MealEntity
import com.example.nutri.data.statistics.entities.RecipeInMeal
import com.example.nutri.domain.statistics.Meal

class MealDatabaseGatewayImpl(
    val database: RecipeDatabase
) : MealDataBaseGateway {


    override suspend fun saveMeal(meal: Meal): Int {
        val mealCategoryId = database.mealDAO()
            .addMealCategory(
                MealCategory(
                    id = null,
                    text = meal.name))

        val mealId = database.mealDAO()
            .addMeal(MealEntity(
                id = null,
                idCategory = mealCategoryId.toInt(), meal.date))


        meal.recipes.forEach {

            it.name?.let{name ->

                database.mealDAO()
                    .addRecipeInMeal(RecipeInMeal(
                        idMeal = mealId.toInt(),
                        idRecipe = database.recipeDAO().getRecipeId(name)))
            }
        }

        return mealId.toInt()
    }
}