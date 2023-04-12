package com.example.nutri.ui.screens.create_recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import com.example.nutri.core.ResultState
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.navigation.NavigationViewModel
import com.example.nutri.domain.recipes.interactor.LocalRecipesInteractor
import com.example.nutri.domain.recipes.interactor.RecipeInteractor
import com.example.nutri.domain.recipes.model.Ingredient
import com.example.nutri.domain.recipes.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor(
    var useCase : LocalRecipesInteractor,
    private var useCaseAnalyze: RecipeInteractor,
    navControllerProvider: NavControllerHolder
) : NavigationViewModel(navControllerProvider){

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
            if (it == list[list.size-1]){ stringOfIngredients += it}
            else stringOfIngredients += "$it and "
        }

        Log.d("STRING PARAM", stringOfIngredients)

        return stringOfIngredients
    }

    fun onSaveButtonPressed() = viewModelScope.launch {
        Log.d(tag, "onSaveButtonPressed     START")

        if (recipe.value.ingredients != null){
            if (recipeName.value.isEmpty()){
                Log.d(tag, "Can't save recipe. Expected recipe name!")

                //Toast.makeText(context, "Enter recipe's name", Toast.LENGTH_LONG).show()
                recipeName.value = recipe.value.ingredients!![0].text
            }

            val id = useCase.saveRecipe(recipe.value, recipeName.value)
            Log.d(tag, "Saved recipe id: $id")
        }

        Log.w(tag, "Can't save empty recipe!!")
    }

    fun onAnalyzeButtonPressed(ingredientParam: String) = viewModelScope.launch{
        Log.d(tag, "onAnalyzeButtonPressed    START")

        val analysisResult = useCaseAnalyze.retrieveRecipe(ingredientParam)
        if (analysisResult is ResultState.Success){
            recipe.value = analysisResult.value
            Log.d("Recipe: ", "\n ${recipe.value}")
        } else showError()
        Log.d(tag, "END")
    }

    private fun showError() {
        TODO("Not yet implemented")
    }
}