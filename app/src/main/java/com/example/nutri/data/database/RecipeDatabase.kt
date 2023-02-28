package com.example.nutri.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nutri.data.database.dao.RecipeDAO
import com.example.nutri.data.entity.RecipeEntity

@Database(entities = [RecipeEntity::class],
    version = 0,
    exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDAO() : RecipeDAO
}