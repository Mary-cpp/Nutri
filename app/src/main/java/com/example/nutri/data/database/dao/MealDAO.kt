package com.example.nutri.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.nutri.data.statistics.entities.MealCategory
import com.example.nutri.data.statistics.entities.MealCommonEntity
import com.example.nutri.data.statistics.entities.MealEntity
import com.example.nutri.data.statistics.entities.RecipeInMeal

@Dao
interface MealDAO {

    @Insert
    suspend fun addMealCategory(it: MealCategory): Long

    @Query("SELECT * FROM meal_categories WHERE id = :id")
    suspend fun getCategoryById(id: String): MealCategory

    @Insert
    suspend fun addMeal(it: MealEntity): Long

    @Query("SELECT * FROM meals")
    suspend fun getMeals(): List<MealEntity>

    @Insert
    suspend fun addRecipeInMeal(it: RecipeInMeal)

    @Query("SELECT * FROM meals WHERE id = :id")
    suspend fun getMealById(id: Int): MealEntity

    @Query("SELECT * FROM recipes_in_meal WHERE id_meal = :idMeal")
    suspend fun getRecipesInMeal(idMeal: String): List<RecipeInMeal>

    @Insert
    suspend fun addRecipesInMeal(list: List<RecipeInMeal>)

    @Transaction
    suspend fun addCommonMeal(it: MealCommonEntity): Long{
        addMealCategory(it.mealCategory)
        val id = addMeal(it.meal)
        addRecipesInMeal(it.recipes)

        return id
    }

    @Transaction
    suspend fun getCommonMeals(): List<MealCommonEntity>{

        val commonMealList = mutableListOf<MealCommonEntity>()

        val mealEntityList = getMeals()

        mealEntityList.forEach {
            commonMealList.add(MealCommonEntity(it,
                getCategoryById(it.id),
                getRecipesInMeal(it.id)
            ) )
        }

        return commonMealList
    }
}