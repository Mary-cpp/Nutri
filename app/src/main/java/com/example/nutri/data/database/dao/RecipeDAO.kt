package com.example.nutri.data.database.dao

import androidx.room.*
import com.example.nutri.data.entity.*

@Dao
interface RecipeDAO {

    @Query("SELECT * FROM recipes")
        suspend fun getRecipes(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun add(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun addIngredients(ingredients: List<IngredientEntity>)

    @Query("SELECT id FROM recipes WHERE name =:recipeName LIMIT 1")
        suspend fun getRecipeId(recipeName : String) : Int

    @Query("SELECT id FROM ingredients WHERE name =:ingredientName LIMIT 1")
        suspend fun getIngredientId(ingredientName: String) : Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun addLabels(labels: List<Label>)

    @Query("DELETE FROM recipes")
        suspend fun clear()

    @Transaction
    @Query("SELECT * FROM recipes")
        suspend fun getFullRecipes(): List<RecipeEntityCommon>

    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :recipeId LIMIT 1")
        suspend fun getRecipeById(recipeId: Int) : RecipeEntityCommon


    @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun addIngredientsOfRecipe(ingredients: List<IngredientInRecipe>)

    @Query("SELECT * FROM ingredients WHERE id = :idIngredient LIMIT 1")
        suspend fun getIngredientById(idIngredient: Int) : IngredientEntity

    @Query("SELECT * FROM recipe_ingredients WHERE id_recipe LIKE :recipeId")
        suspend fun getRecipeIngredients(recipeId: Int) : List<IngredientInRecipe>

    @Delete
        suspend fun delete(model: RecipeEntity)
}