package com.example.nutri.ui.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.nutri.ui.recipe.viewmodel.RecipeAnalyzeViewModel
import com.example.nutri.ui.recipe.viewmodel.RecipeAnalyzeViewModel.ViewPages

@Composable
fun Analyzer (viewModel: RecipeAnalyzeViewModel) {

    val recipe by remember {
        viewModel.recipe
    }

   // Log.w()


    Column(modifier = Modifier.padding(20.dp)) {

        val context = LocalContext.current

        if (ViewPages.LISTOFRECIPES != viewModel.viewPage.value){
            AnalyzerMainInterface(viewModel = viewModel)
        }

        if (ViewPages.RECIPE == viewModel.viewPage.value) {
            RecipeDisplay(recipe = recipe)

            Button(
                onClick =
                {
                    viewModel.onSaveButtonPressed()

                    Toast
                        .makeText(
                            context,
                            "Analysis was saved to recipe",
                            Toast.LENGTH_SHORT)
                        .show()
                },
                modifier = Modifier.padding(start = 16.dp)
            )
            {
                Text("Save")
            }
        }

        if(ViewPages.SAVED == viewModel.viewPage.value){
            Button(onClick = {
                viewModel.onMyRecipesButtonPressed() },
                modifier = Modifier.padding(start = 16.dp)) {
                Text ("My Recipes")
            }
        }

        if (ViewPages.LISTOFRECIPES == viewModel.viewPage.value) {
            MyRecipesDisplay(onGoHome = viewModel::onGoHomeButtonPressed, recipeList = viewModel.recipeList.value)



        }
    }
}