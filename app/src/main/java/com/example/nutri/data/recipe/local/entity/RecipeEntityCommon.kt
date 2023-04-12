package com.example.nutri.data.recipe.local.entity

import androidx.room.Embedded
import androidx.room.Relation

class RecipeEntityCommon(
    @Embedded
    val recipeEntity: RecipeEntity,

    @Relation(
        entity = LabelsInRecipe::class,
        parentColumn = "id",
        entityColumn = "id_recipe")
    val labels: List<LabelsInRecipe>,

    @Relation(
        entity = IngredientInRecipe::class,
        parentColumn = "id",
        entityColumn = "id_recipe")
    val ingredientsInRecipe: List<IngredientInRecipe>,

    @Relation(
        entity = NutrientsInRecipe::class,
        parentColumn = "id",
        entityColumn = "recipe_id"
    )
    val nutrientsInRecipe: List<NutrientsInRecipe>

)