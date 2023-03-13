package com.example.nutri.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.interactor.LocalRecipesInteractor
import com.example.nutri.domain.model.Ingredient
import com.example.nutri.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor(
    var useCase : LocalRecipesInteractor
) : ViewModel(){

    private val tag = "CreateRecipeViewModel"

    var listOfIngredients = mutableStateListOf<Ingredient>()

    val recipe: MutableState<Recipe> = mutableStateOf(Recipe())
    val recipeName: MutableState<String> = mutableStateOf("")

    fun onRemoveButtonPressed(
        ingredient: Ingredient
    ){
        listOfIngredients.remove(ingredient)
    }

    fun ingredientsToString(
        list: SnapshotStateList<Ingredient>
    ): String {

        var stringOfIngredients = ""

        list.forEach {
            stringOfIngredients += "$it and"
        }

        Log.d("STRING PARAM", stringOfIngredients)

        return stringOfIngredients
    }

    fun onSaveButtonPressed() = viewModelScope.launch {
        Log.d(tag, "onSaveButtonPressed     START")
        if (recipeName.value.isEmpty()){
            Log.d(tag, "Can't save recipe. Expected recipe name!")

            //Toast.makeText(context, "Enter recipe's name", Toast.LENGTH_LONG).show()
            recipeName.value = recipe.value.ingredients!![0].text

            return@launch
        }


        useCase.saveRecipe(recipe.value, recipeName.value)
    }
}