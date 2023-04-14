package com.example.nutri.ui.screens.my_recipes.composables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import com.example.nutri.ui.screens.my_recipes.RecipesViewModel
import com.example.nutri.ui.theme.NutriTheme
import kotlinx.coroutines.launch

enum class SortActions(val text: String, val value: Int){
    NAME(text = "Sort by name", value = 1),
    CALORIES(text = "Sort by calories", value = 2)
}

@Composable
fun SortAndFilter(vm: RecipesViewModel){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly){
        Box(contentAlignment = Alignment.Center){
            var sortButtonText by remember { mutableStateOf("Sort") }
            var selectedSortFilter by remember { mutableStateOf<SortActions?>(null) }
            var isSortExpanded by remember { mutableStateOf(false)}
            val sortMenuItems = SortActions.values()

            LaunchedEffect(key1 = sortButtonText){
                this.launch {
                    selectedSortFilter?.let{
                        vm.recipeList.value = vm.sortRecipes(it.value)
                        Log.i("SortAndFilter", "Sorting by ${it.text}")
                    }
                }
            }

            Button(onClick = { isSortExpanded = !isSortExpanded },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondaryVariant),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.elevation(4.dp),
            content = {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.sort48px),
                    tint = Color.Black,
                    contentDescription = "SortIcon",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 8.dp))

                Text(text = sortButtonText, color = Color.Black, modifier = Modifier.padding(end = 16.dp))
            }
        )
            DropdownMenu(
                expanded = isSortExpanded,
                onDismissRequest = { isSortExpanded = false }) {
                sortMenuItems.forEach{
                    DropdownMenuItem(onClick = {
                        selectedSortFilter = it
                        sortButtonText = it.text
                    }) {
                        Text(text = it.text)
                    }
                }
            }
        }


        Button(onClick = { /*TODO(Filter)*/ },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary),
            shape = RoundedCornerShape(24.dp),
            elevation = ButtonDefaults.elevation(4.dp)
        ) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.filter_alt48px),
                tint = Color.Black,
                contentDescription = "SortIcon",
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp))
            Text(text = "Filter", color = Color.Black)
        }
    }
}

@Preview
@Composable
fun SortAndFilterButtonsPreview(){
    NutriTheme {
        //SortAndFilter()
    }
}