package com.example.nutri.data.database.dao

import androidx.room.*
import com.example.nutri.data.entity.RecipeEntity

@Dao
interface RecipeDAO {
    @Query("SELECT * FROM recipes")
        fun getRecipes(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun add(recipe: RecipeEntity)

    @Query("DELETE FROM recipes")
        fun clear()

    @Delete
        fun delete(model: RecipeEntity)
}