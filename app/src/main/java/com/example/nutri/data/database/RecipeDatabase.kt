package com.example.nutri.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nutri.data.database.dao.RecipeDAO
import com.example.nutri.data.entity.IngredientEntity
import com.example.nutri.data.entity.RecipeEntity

@Database(entities = [RecipeEntity::class, IngredientEntity::class],
    version = 2,
    exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDAO() : RecipeDAO

    companion object {

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context) : RecipeDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    RecipeDatabase :: class.java,
                    "recipe_database"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}