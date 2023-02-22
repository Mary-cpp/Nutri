package com.example.nutri.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nutri.repository.database.dao.RecipeDAO
import com.example.nutri.domain.entity.Recipe

@Database(entities = [Recipe::class], version = 0, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDAO() : RecipeDAO
}