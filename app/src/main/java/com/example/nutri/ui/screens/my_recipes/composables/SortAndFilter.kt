package com.example.nutri.ui.screens.my_recipes.composables

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

enum class SortAction(val text: String){
    NAME_ASC(text = "Sort by name"),
    NAME_DESC(text = "Sort by name, descending"),
    CALORIES_ASC(text = "Sort by calories"),
    CALORIES_DESC(text = "Sort by calories, descending"),
}

@Composable
fun SortAndFilter(vm: RecipesViewModel){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly){
        Box(contentAlignment = Alignment.Center){
            var sortButtonText by remember { mutableStateOf("Sort") }
            var selectedSortFilter by remember { mutableStateOf<SortAction?>(null) }
            var isSortExpanded by remember { mutableStateOf(false)}
            val sortMenuItems = SortAction.values()

            LaunchedEffect(key1 = sortButtonText){
                this.launch {
                    selectedSortFilter?.let{
                        vm.onSortListSelectedItemChanged(it)
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

                Text(
                    text = sortButtonText,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 16.dp)
                        .wrapContentWidth()
                        .widthIn(min = 40.dp, max = 88.dp)
                        .wrapContentHeight()
                )
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