package com.example.nutri.ui.screens.edit_recipe

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.data.recipe.remote.dto.Ingredient.Companion.mapToDomainIngredients
import com.example.nutri.domain.recipes.interactor.LocalRecipesInteractor
import com.example.nutri.domain.recipes.interactor.RecipeInteractor
import com.example.nutri.domain.recipes.model.Ingredient
import com.example.nutri.domain.recipes.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditRecipeViewModel @Inject constructor(
    private var useCase: LocalRecipesInteractor,
    private var useCaseAnalyze: RecipeInteractor
) : ViewModel(){

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
    = CoroutineScope(Dispatchers.Main).launch {
        Log.d(tag, "onSaveButtonPressed     START")

        analyzeEdited(ingredientList)

        if (recipeOnEdit.value.ingredients != null){
            if (nameOnEdit.value.isEmpty()){
                Log.d(tag, "Can't save recipe. Expected recipe name!")

                //Toast.makeText(context, "Enter recipe's name", Toast.LENGTH_LONG).show()
                nameOnEdit.value = recipeOnEdit.value.ingredients!![0].text
            }

            val id = useCase.saveRecipe(recipeOnEdit.value, nameOnEdit.value)
            Log.d(tag, "${useCase.getCommonRecipe(id)}")
        }

        Log.w(tag, "Can't save empty recipe!!")
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

    private fun analyzeEdited(
        list: SnapshotStateList<Ingredient>
    ) = viewModelScope.launch{

        Log.d(tag, "onAnalyzeButtonPressed    START")

        val ingredientParam = ingredientsToString(list)

        if (ingredientParam.isEmpty()){
            throw IllegalArgumentException("Ingredient param is null")
        }
        else { recipeOnEdit.value = useCaseAnalyze.retrieveRecipe(ingredientParam)

            Log.d("Recipe: ", "\n ${recipeOnEdit.value}")
        }
        Log.d(tag, "END")
    }
}