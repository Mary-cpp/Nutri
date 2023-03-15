package com.example.nutri.data.database.dao

import androidx.room.*
import com.example.nutri.data.entity.Label
import com.example.nutri.data.entity.RecipeEntity
import com.example.nutri.data.entity.RecipeEntityCommon

@Dao
interface RecipeDAO {

    @Query("SELECT * FROM recipes")
        fun getRecipes(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun add(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun addLabels(labels: List<Label>)

    @Query("DELETE FROM recipes")
        fun clear()

    @Transaction
    @Query("SELECT * FROM recipes")
        fun getFullRecipes(): List<RecipeEntityCommon>

    @Delete
        fun delete(model: RecipeEntity)
}