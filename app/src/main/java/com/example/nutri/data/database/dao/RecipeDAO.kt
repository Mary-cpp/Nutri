package com.example.nutri.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nutri.data.entity.RecipeEntity

@Dao
interface RecipeDAO {
    @Query("SELECT * FROM recipes")
        fun getRecipes(): LiveData<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun add(recipe: List<RecipeEntity>)

    @Query("DELETE * FROM recipes")
        fun clear()

    @Delete
        fun delete(model: RecipeEntity)


}