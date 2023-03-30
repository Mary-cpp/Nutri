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

    @Query("SELECT * FROM meals")
    suspend fun getMeals(): List<MealEntity>

    @Query("SELECT * FROM meals WHERE date(date / 1000,'unixepoch') = date(:date / 1000,'unixepoch')")
    suspend fun getMealsByDate(date: Date): List<MealEntity>

    @Query("SELECT * FROM meals WHERE date(date) = date(:date)")
    suspend fun getMealsByDateText(date: String): List<MealEntity>
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addRecipeInMeal(it: RecipeInMeal)

    @Query("SELECT * FROM meals WHERE id = :id")
    suspend fun getMealById(id: Int): MealEntity

    @Query("SELECT * FROM recipes_in_meal WHERE id_meal = :idMeal")
    suspend fun getRecipesInMeal(idMeal: String): List<RecipeInMeal>

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
    suspend fun getCommonMeals(): List<MealCommonEntity>{

        val commonMealList = mutableListOf<MealCommonEntity>()

        val mealEntityList = getMeals()

        if(mealEntityList.isNotEmpty()){
            mealEntityList.forEach {
                commonMealList.add(
                    MealCommonEntity(
                        it,
                    getCategoryById(it.idCategory),
                    getRecipesInMeal(it.id)
                ) )
            }
        }

        return commonMealList
    }

    @Transaction
    suspend fun getCommonMealsByDate(date: String): List<MealCommonEntity>{
        val commonMealList = mutableListOf<MealCommonEntity>()

        val mealEntityList = getMealsByDateText(date = date)

        if(mealEntityList.isNotEmpty()){
            mealEntityList.forEach {
                commonMealList.add(
                    MealCommonEntity(
                        it,
                        getCategoryById(it.idCategory),
                        getRecipesInMeal(it.id)
                    ) )
            }
        }

        return commonMealList
    }
}