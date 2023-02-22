package com.example.nutri.ui.recipe.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nutri.data.ApiGatewayImpl
import com.example.nutri.domain.RecipeUseCase
import com.example.nutri.domain.entity.Recipe

class RecipeListViewModel  (
    private val apiImpl: ApiGatewayImpl,
    var recipeUseCase: RecipeUseCase
   ) : ViewModel() {

    lateinit var recipes: List<Recipe>

    override fun onCleared() {
        super.onCleared()
        TODO("Implement resource controlling")
    }

    companion object Data {
       /* fun getData(): MutableState<List<Recipe>>{
            return mutableStateOf(recipes)
        }

        fun loadData(): MutableState<List<Recipe>>{
            return mutableStateOf(recipes)

        }*/


    }
}