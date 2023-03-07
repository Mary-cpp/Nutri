package com.example.nutri.ui.recipe.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.interactor.RecipeInteractor
import com.example.nutri.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "VIEW_MODEL"

@HiltViewModel
class RecipeAnalyzeViewModel @Inject constructor (
    private var recipeInteractor: RecipeInteractor
) : ViewModel() {

    enum class ViewPages { INIT, RECIPE, SAVED, LISTOFRECIPES}

    val viewPage = mutableStateOf(ViewPages.RECIPE)

    val recipe : MutableState<Recipe> = mutableStateOf(Recipe())
    val recipeList : MutableState<List<Recipe>> = mutableStateOf(listOf())

    val nameField : MutableState<String> = mutableStateOf("")

    init {
        viewPage.value = ViewPages.INIT
    }

    fun onAnalyzeButtonPressed(ingredientParam: String) = viewModelScope.launch{
        Log.d(TAG, "onAnalyzeButtonPressed    START")

        if (ingredientParam.isEmpty()){
            throw IllegalArgumentException("Ingredient param is null")
        }
        else { recipe.value = recipeInteractor.analyzeRecipe(ingredientParam)

           viewPage.value = ViewPages.RECIPE
        }

        Log.d(TAG, "END")
    }

    fun onSaveButtonPressed() = viewModelScope.launch {
        Log.d(TAG, "onSaveButtonPressed     START")
        if (nameField.value.isEmpty()){
            Log.d(TAG, "Can't save recipe. Expected recipe name!")

            //Toast.makeText(context, "Enter recipe's name", Toast.LENGTH_LONG).show()

            //return@launch

            nameField.value = recipe.value.ingredients?.get(0)?.text ?: "Recipe name"
        }


        viewPage.value = ViewPages.SAVED
        recipeInteractor.saveRecipe(recipe.value, nameField.value)
    }

    fun onMyRecipesButtonPressed() = viewModelScope.launch {
        Log.d(TAG, "onMyRecipesButtonPressed        START")

        viewPage.value = ViewPages.LISTOFRECIPES
        recipeList.value = recipeInteractor.receiveRecipes()

        Log.d(TAG, "onMyRecipesButtonPressed        END")
    }

    fun onGoHomeButtonPressed() {
        Log.d(TAG, "onGoHomeButtonClicked        START")

        viewPage.value = ViewPages.INIT

        Log.d(TAG, "onGoHomeButtonClicked        END")
    }
}