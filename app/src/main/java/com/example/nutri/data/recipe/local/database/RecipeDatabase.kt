package com.example.nutri.data.recipe.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nutri.data.recipe.local.database.dao.UserDAO
import com.example.nutri.data.bmi.entity.ActivityTypeEntity
import com.example.nutri.data.bmi.entity.DietPlanEntity
import com.example.nutri.data.bmi.entity.UserEntity
import com.example.nutri.data.recipe.local.database.dao.RecipeDAO
import com.example.nutri.data.recipe.local.entity.*

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
        UserEntity::class
    ],
    version = 10,
    exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDAO() : RecipeDAO

    abstract fun userDAO() : UserDAO

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