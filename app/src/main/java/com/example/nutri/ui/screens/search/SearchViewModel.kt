package com.example.nutri.ui.screens.search

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.nutri.domain.recipes.interactor.LocalRecipesInteractor
import com.example.nutri.domain.recipes.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: LocalRecipesInteractor
) : ViewModel() {

    private val tag = "SearchViewModel"

    val foundRecipes : MutableState<List<Recipe>> = mutableStateOf(listOf())

    fun getRecipes(name: String) = CoroutineScope(Dispatchers.Main).launch{

        Log.d(tag, "getRecipes     START")

        val listOfRecipes = useCase.getRecipesLike(name)

        listOfRecipes?.let{
            foundRecipes.value = listOfRecipes


        }

        Log.d(tag, "Recipe: ${foundRecipes.value[0].name}")

        Log.d(tag, "getRecipes     END")
    }
}