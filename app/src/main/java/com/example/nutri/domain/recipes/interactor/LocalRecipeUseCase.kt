package com.example.nutri.domain.recipes.interactor

import com.example.nutri.core.ResultState
import com.example.nutri.domain.recipes.RecipeDatabaseGateway
import com.example.nutri.domain.recipes.model.Recipe
import com.example.nutri.ui.screens.my_recipes.composables.SortAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalRecipeUseCase @Inject constructor(
    private val db: RecipeDatabaseGateway
    ): LocalRecipesInteractor {

    override suspend fun saveRecipe(recipe: Recipe, recipeName: String): String {
        return db.saveRecipeInfo(recipe, recipeName)
    }

    override suspend fun receiveRecipes() : Flow<ResultState<List<Recipe>>> {
        return db.getRecipesListFlow()
    }

    override suspend fun getCommonRecipe(recipeId: String): Flow<ResultState<Recipe>> {
        return db.getRecipeById(id = recipeId)
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        return db.deleteRecipe(recipe)
    }

    override suspend fun getRecipesWithNameLike(name: String): List<Recipe>? {
        return db.getRecipesWithNameLike(name)
    }

    override fun sortRecipesBy(sortAction: SortAction, list: List<Recipe>) : Flow<List<Recipe>> {
        return when(sortAction){
            SortAction.NAME_ASC -> flow {
                emit(list.sortedBy { it.name })
            }.flowOn(Dispatchers.Unconfined)
            SortAction.NAME_DESC -> flow {
                emit(list.sortedByDescending { it.name })
            }.flowOn(Dispatchers.Unconfined)
            SortAction.CALORIES_ASC -> flow {
                emit(list.sortedBy { it.calories })
            }.flowOn(Dispatchers.Unconfined)
            SortAction.CALORIES_DESC -> flow {
                emit(list.sortedByDescending { it.calories })
            }.flowOn(Dispatchers.Unconfined)
        }
    }
}