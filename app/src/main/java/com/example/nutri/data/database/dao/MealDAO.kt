package com.example.nutri.data.database.dao

import androidx.room.*
import com.example.nutri.data.statistics.entities.MealCategory
import com.example.nutri.data.statistics.entities.MealCommonEntity
import com.example.nutri.data.statistics.entities.MealEntity
import com.example.nutri.data.statistics.entities.RecipeInMeal
import java.util.*

@Dao
interface MealDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMealCategory(it: MealCategory): Long

    @Query("SELECT * FROM meal_categories WHERE id = :id")
    suspend fun getCategoryById(id: String): MealCategory

    @Query("SELECT id FROM meal_categories WHERE text = :name")
    suspend fun getCategoryIdByName(name: String): String

    @Insert
    suspend fun addMeal(it: MealEntity): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addRecipeInMeal(it: RecipeInMeal)

    @Query("SELECT * FROM meals WHERE id = :id")
    suspend fun getMealById(id: Int): MealEntity

    @Query("SELECT id FROM meals WHERE date = :date AND id_category = :idCategory")
    suspend fun getMealByDateAndCategory(date: String, idCategory: String) : String?

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
    @Query("SELECT * FROM meals")
    suspend fun getCommonMeals(): List<MealCommonEntity>

    @Transaction
    @Query("SELECT * FROM meals WHERE date(date) = date(:date)")
    suspend fun getCommonMealsByDate(date: String): List<MealCommonEntity>
}