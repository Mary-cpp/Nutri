package com.example.nutri.ui.screens.edit_recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.nutri.core.ResultState
import com.example.nutri.ui.navigation.NavControllerHolder
import com.example.nutri.ui.navigation.NavigationViewModel
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
    navControllerProvider: NavControllerHolder
) : NavigationViewModel(navControllerProvider), DefaultLifecycleObserver{

    private val tag = "CreateRecipeViewModel"

    var ingredientList = mutableStateListOf<Ingredient>()
    val nameOnEdit = mutableStateOf("")
    val isLoading: MutableState<Boolean> by lazy{ mutableStateOf(true) }
    var id: String = ""

    private val recipeOnEdit = mutableStateOf(Recipe())

    override fun onResume(owner: LifecycleOwner) {
        onEditRecipePageLoaded(id)
    }

    private fun onEditRecipePageLoaded(id: String) = viewModelScope.launch{
        Log.d(tag, "onEditRecipePageLoaded     START")
        useCase.getCommonRecipe(id).collect{ result ->
            if (result is ResultState.Success){
                recipeOnEdit.value = result.value
                nameOnEdit.value = result.value.name as String
                result.value.ingredients?.get(0)?.let{
                    ingredientList.addAll(it.mapToDomainIngredients())
                }
                isLoading.value = false
            }
        }
    }

    fun onSaveEditedRecipeButtonPressed()
    = viewModelScope.launch {
        Log.d(tag, "onSaveButtonPressed     START")

        val result = analyzeEditedAsync(ingredientList).await()
        if (result is ResultState.Success){
            result.value.id = recipeOnEdit.value.id

            if (result.value.ingredients != null){
                if (nameOnEdit.value.isEmpty()){
                    nameOnEdit.value = result.value.ingredients[0].text
                }

                val id = useCase.saveRecipe(result.value, nameOnEdit.value)
                Log.d(tag, "Saved recipe id: $id")
            }
        }
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