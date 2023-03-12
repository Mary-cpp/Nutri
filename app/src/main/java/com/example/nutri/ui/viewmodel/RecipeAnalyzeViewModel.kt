package com.example.nutri.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.interactor.RecipeInteractor
import com.example.nutri.domain.interactor.LocalRecipesInteractor
import com.example.nutri.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "VIEW_MODEL"

@HiltViewModel
class RecipeAnalyzeViewModel @Inject constructor (
    private var useCaseAnalyze: RecipeInteractor,
    private var useCaseSave: LocalRecipesInteractor
) : ViewModel() {

    enum class ViewPages { INIT, RECIPE, SAVED, LISTOFRECIPES}
    val viewPage = mutableStateOf(ViewPages.RECIPE)

    val recipe : MutableState<Recipe> = mutableStateOf(Recipe())


    val nameField : MutableState<String> = mutableStateOf("")

    init {
        viewPage.value = ViewPages.INIT
    }

    fun onAnalyzeButtonPressed(ingredientParam: String) = viewModelScope.launch{
        Log.d(TAG, "onAnalyzeButtonPressed    START")

        if (ingredientParam.isEmpty()){
            throw IllegalArgumentException("Ingredient param is null")
        }
        else { recipe.value = useCaseAnalyze.retrieveRecipe(ingredientParam)

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

            nameField.value = "REcipeName"
        }


        viewPage.value = ViewPages.SAVED
        useCaseSave.saveRecipe(recipe.value, nameField.value)
    }

    fun onGoHomeButtonClicked() {
        Log.d(TAG, "onGoHomeButtonClicked        START")

        viewPage.value = ViewPages.INIT

        Log.d(TAG, "onGoHomeButtonClicked        END")
    }
}