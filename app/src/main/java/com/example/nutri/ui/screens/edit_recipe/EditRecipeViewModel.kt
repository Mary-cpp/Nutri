package com.example.nutri.ui.screens.edit_recipe

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.nutri.domain.recipes.model.Ingredient

class EditRecipeViewModel(

) : ViewModel(){

    val ingredientList = mutableStateListOf<Ingredient>()
}