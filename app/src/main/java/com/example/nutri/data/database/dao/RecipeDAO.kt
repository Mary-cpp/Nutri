package com.example.nutri.data.database.dao

import androidx.room.*
import com.example.nutri.data.recipe.local.entity.*

@Dao
interface RecipeDAO {

    @Query("SELECT * FROM recipes")
    suspend fun getRecipes(): List<RecipeEntity>

    @Query("SELECT * FROM recipes WHERE name LIKE '%' || :name || '%'")
    suspend fun getRecipesWithNameLike(name: String): List<RecipeEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipeEntity(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIngredients(ingredients: List<IngredientEntity>)

    @Query("SELECT id FROM recipes WHERE name =:recipeName LIMIT 1")
    suspend fun getRecipeId(recipeName : String) : String

    @Query("SELECT * FROM recipes WHERE id =:recipeId")
    suspend fun getRecipeById(recipeId : String) : RecipeEntity

    @Query("SELECT id FROM ingredients WHERE name =:ingredientName LIMIT 1")
    suspend fun getIngredientId(ingredientName: String) : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLabels(labels: List<Label>)

    @Query("SELECT * FROM Label WHERE id = :labelId")
    suspend fun getLabelById(labelId: Int): Label

    @Query("SElECT id FROM Label WHERE text = :labelText LIMIT 1")
    suspend fun getLabelId(labelText: String): Int

    @Query("DELETE FROM recipes")
    suspend fun clear()

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun addLabelsForRecipe(labels: List<LabelsInRecipe>)


    @Transaction
    @Query("SELECT * FROM recipe_labels WHERE id_recipe = :idRecipe")
    suspend fun getRecipeWithLabels(idRecipe: String): List<LabelsInRecipe>


    @Transaction
    @Query("SELECT * FROM recipes")
    suspend fun getCommonRecipe(recipeId: String) : RecipeEntityCommon{

        return RecipeEntityCommon(
            getRecipeById(recipeId),
            getRecipeWithLabels(recipeId),
            getRecipeIngredients(recipeId),
        )
    }

    @Transaction
    suspend fun addCommonRecipe(
        recipe: RecipeEntityCommon,
    ){
        addRecipeEntity(recipe.recipeEntity)
        addLabelsForRecipe(recipe.labels)
        addIngredientsOfRecipe(recipe.ingredientsInRecipe)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIngredientsOfRecipe(ingredients: List<IngredientInRecipe>)

    @Query("SELECT * FROM ingredients WHERE id = :idIngredient LIMIT 1")
    suspend fun getIngredientById(idIngredient: Int) : IngredientEntity

    @Transaction
    @Query("SELECT * FROM recipe_ingredients WHERE id_recipe = :idRecipe")
    suspend fun getRecipeIngredients(idRecipe: String) : List<IngredientInRecipe>

    @Delete
    suspend fun deleteRecipe(model: RecipeEntity)
}