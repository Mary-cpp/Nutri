package com.example.nutri.data.database.dao

import androidx.room.*
import com.example.nutri.data.entity.*

@Dao
interface RecipeDAO {

    @Query("SELECT * FROM recipes")
        fun getRecipes(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun add(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun addIngredients(ingredients: List<IngredientEntity>)

    @Query("SELECT id FROM recipes WHERE name =:recipeName LIMIT 1")
        fun getRecipeId(recipeName : String) : Int

    @Query("SELECT id FROM ingredients WHERE name =:ingredientName LIMIT 1")
        fun getIngredientId(ingredientName: String) : Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun addLabels(labels: List<Label>)

    @Query("DELETE FROM recipes")
        fun clear()

    @Transaction
    @Query("SELECT * FROM recipes")
        fun getFullRecipes(): List<RecipeEntityCommon>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun addIngredientsOfRecipe(ingredients: List<IngredientInRecipe>)

    @Query("SELECT * FROM recipe_ingredients WHERE id_recipe LIKE :recipeId")
        fun getRecipeIngredients(recipeId: Int) : List<IngredientInRecipe>

    @Delete
        fun delete(model: RecipeEntity)
}