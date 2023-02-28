package com.example.nutri.ui.recipe.viewmodel

import androidx.lifecycle.ViewModel
import com.example.nutri.data.DataBaseGatewayImpl
import com.example.nutri.domain.RecipeUseCaseImp

class RecipeListViewModel  (
    private val dbImpl: DataBaseGatewayImpl,
    var recipeUseCase: RecipeUseCaseImp
   ) : ViewModel() {

    init {

    }


    override fun onCleared() {
        super.onCleared()
        TODO("Implement resource controlling")
    }

}