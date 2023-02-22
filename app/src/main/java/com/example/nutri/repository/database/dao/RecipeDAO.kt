package com.example.nutri.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nutri.domain.entity.Recipe

@Dao
interface RecipeDAO {
    @Query("SELECT * FROM recipes")
        fun getRecipes(): LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun add(recipe: List<Recipe>)

    @Query("DELETE * FROM recipes")
        fun clear()

    @Delete
        fun delete(model: Recipe)


}