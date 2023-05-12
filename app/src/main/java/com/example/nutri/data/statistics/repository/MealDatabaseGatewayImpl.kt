package com.example.nutri.data.statistics.repository

import android.util.Log
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.data.recipe.local.repository.DataBaseGatewayImpl
import com.example.nutri.data.statistics.entities.MealCategory
import com.example.nutri.data.statistics.entities.MealCommonEntity
import com.example.nutri.data.statistics.entities.MealEntity
import com.example.nutri.data.statistics.entities.RecipeInMeal
import com.example.nutri.domain.statistics.Meal
import com.example.nutri.ui.screens.my_recipes.TAG
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
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

        var mealCategoryId = database.mealDAO().getCategoryIdByName(meal.name)
        if (mealCategoryId == null){
            mealCategoryId = mealCategoryUId
        }

        val mealCategory = MealCategory(
            id = mealCategoryId,
            db_id = null,
            text = meal.name
        )
        val mealEntity = MealEntity(
            id = mealUId,
            db_id = null,
            idCategory = mealCategoryId,
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

        var dbMealId: String?

        category.let{
            dbMealId = database
                .mealDAO()
                .getMealByDateAndCategory(
                    date = meal.date,
                    idCategory = it
                )
        }

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
                                idMeal = dbMealId!!,
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

    override fun getMealsList(date: String): Flowable<List<Meal>> {
        Log.d(tag, "getMealsList START")

        return database.mealDAO().getCommonMealsByDate(date)
            .subscribeOn(Schedulers.io())
            .switchMap { commonMeals ->
                Log.i(tag, commonMeals.size.toString())
                if (commonMeals.isEmpty()) Flowable.just(emptyList<Meal>())
                Flowable.just(commonMeals.map {
                    Meal(
                        name = it.mealCategory.text,
                        date = it.meal.date,
                        recipes = it.recipes.map { recipe ->
                            DataBaseGatewayImpl(database).mapToRecipe(
                                database.recipeDAO().getRecipeById(recipe.idRecipe)
                            )
                        }.toMutableList()
                    )
                })
            }
            .doOnComplete {
            }
    }
}