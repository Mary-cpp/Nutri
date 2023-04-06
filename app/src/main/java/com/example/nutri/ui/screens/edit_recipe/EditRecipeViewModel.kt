package com.example.nutri.ui.screens.edit_recipe

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import com.example.nutri.core.MyNavController
import com.example.nutri.core.NavigationViewModel
import com.example.nutri.data.recipe.remote.dto.Ingredient.Companion.mapToDomainIngredients
import com.example.nutri.domain.recipes.interactor.LocalRecipesInteractor
import com.example.nutri.domain.recipes.interactor.RecipeInteractor
import com.example.nutri.domain.recipes.model.Ingredient
import com.example.nutri.domain.recipes.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class EditRecipeViewModel @Inject constructor(
    private var useCase: LocalRecipesInteractor,
    private var useCaseAnalyze: RecipeInteractor,
    navControllerProvider: MyNavController
) : NavigationViewModel(navControllerProvider){

    private val tag = "CreateRecipeViewModel"

    var ingredientList = mutableStateListOf<Ingredient>()
    val nameOnEdit = mutableStateOf("")

    private val recipeOnEdit = mutableStateOf(Recipe())

    fun onEditRecipePageLoaded(id: String) = viewModelScope.launch{

        ingredientList.clear()

        Log.d(tag, "onEditRecipePageLoaded     START")
        val fetchedRecipe = useCase.getCommonRecipe(id)
        recipeOnEdit.value = fetchedRecipe
        nameOnEdit.value = fetchedRecipe.name.toString()

        fetchedRecipe.ingredients?.get(0)?.let { ingredientList.addAll( it.mapToDomainIngredients()) }
    }

    fun onSaveEditedRecipeButtonPressed()
    = viewModelScope.launch {
        Log.d(tag, "onSaveButtonPressed     START")

        val result = analyzeEditedAsync(ingredientList).await()
        result.id = recipeOnEdit.value.id

        if (result.ingredients != null){
            if (nameOnEdit.value.isEmpty()){
                Log.d(tag, "Can't save recipe. Expected recipe name!")

                //Toast.makeText(context, "Enter recipe's name", Toast.LENGTH_LONG).show()
                nameOnEdit.value = result.ingredients[0].text
            }

            val id = useCase.saveRecipe(result, nameOnEdit.value)
            Log.d(tag, "${useCase.getCommonRecipe(id)}")
        }
        Log.w(tag, "Can't save empty recipe!!")
    }

    fun onDeleteButtonPresseed(){
    }

    private fun ingredientsToString(
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

    fun onRemoveButtonPressed(
        ingredient: Ingredient
    ){
        ingredientList.remove(ingredient)
    }

    private val analyzeExceptionHandler
    = CoroutineExceptionHandler { coroutineContext, throwable ->
        throw IllegalArgumentException("$coroutineContext : Can not analyze empty recipe $throwable") }

    private fun analyzeEditedAsync(
        list: SnapshotStateList<Ingredient>
    ) = CoroutineScope(
        context = Dispatchers.Main,
    ).async(analyzeExceptionHandler){

        Log.d(tag, "onAnalyzeButtonPressed    START")

        val ingredientParam = ingredientsToString(list)

        useCaseAnalyze.retrieveRecipe(ingredientParam)
    }
}