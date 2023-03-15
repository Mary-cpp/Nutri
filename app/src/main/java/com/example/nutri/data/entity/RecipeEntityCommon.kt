package com.example.nutri.data.entity

import androidx.room.Embedded
import androidx.room.Relation

class RecipeEntityCommon(
    @Embedded
    val recipeEntity: RecipeEntity,
    @Relation
        (parentColumn = "id", entity = Label::class, entityColumn = "id_recipe")
    val labels: List<Label>,

    @Relation
        (parentColumn = "id", entity = IngredientInRecipe::class, entityColumn = "id_recipe")
    val ingredientsInRecipe: List<IngredientInRecipe>

)