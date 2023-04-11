package com.example.nutri.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nutri.data.database.dao.UserDAO
import com.example.nutri.data.bmi.entity.ActivityTypeEntity
import com.example.nutri.data.bmi.entity.DietPlanEntity
import com.example.nutri.data.bmi.entity.UserEntity
import com.example.nutri.data.database.dao.MealDAO
import com.example.nutri.data.database.dao.RecipeDAO
import com.example.nutri.data.recipe.local.entity.*
import com.example.nutri.data.statistics.Converter
import com.example.nutri.data.statistics.entities.MealCategory
import com.example.nutri.data.statistics.entities.MealEntity
import com.example.nutri.data.statistics.entities.RecipeInMeal

@Database(
    entities =
    [
        RecipeEntity::class,
        IngredientEntity::class,
        Label::class,
        IngredientInRecipe::class,
        LabelsInRecipe::class,
        ActivityTypeEntity::class,
        DietPlanEntity::class,
        UserEntity::class,
        MealEntity::class,
        MealCategory::class,
        RecipeInMeal::class,
        NutrientEntity::class,
        NutrientsInRecipe::class
    ],
    version = 18,
    exportSchema = false)
@TypeConverters(Converter::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDAO() : RecipeDAO
    abstract fun userDAO() : UserDAO
    abstract fun mealDAO() : MealDAO

    companion object {

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context) : RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    RecipeDatabase :: class.java,
                    "recipe_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}