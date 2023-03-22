package com.example.nutri.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nutri.data.statistics.entities.MealCategory
import com.example.nutri.data.statistics.entities.MealEntity
import com.example.nutri.data.statistics.entities.RecipeInMeal

@Dao
interface MealDAO {

    @Insert
    suspend fun addMealCategory(it: MealCategory): Long

    @Insert
    suspend fun addMeal(it: MealEntity): Long

    @Insert
    suspend fun addRecipeInMeal(it: RecipeInMeal)

    @Query("SELECT * FROM meals WHERE id = :id")
    suspend fun getMealById(id: Int): MealEntity

    @Query("SELECT * FROM recipes_in_meal WHERE id_meal = :idMeal")
    suspend fun getRecipesInMeal(idMeal: Int): List<RecipeInMeal>
}