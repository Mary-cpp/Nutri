package com.example.nutri.data.recipe.local.entity

import androidx.room.Embedded
import androidx.room.Relation

class RecipeEntityCommon(
    @Embedded
    val recipeEntity: RecipeEntity,
    @Relation
        (parentColumn = "id", entity = LabelsInRecipe::class, entityColumn = "id_recipe")
    val labels: List<LabelsInRecipe>,

    @Relation
        (parentColumn = "id", entity = IngredientInRecipe::class, entityColumn = "id_recipe")
    val ingredientsInRecipe: List<IngredientInRecipe>

)