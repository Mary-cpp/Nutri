package com.example.nutri.ui.screens.my_recipes.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import com.example.nutri.ui.screens.my_recipes.RecipesListViewModel
import kotlinx.coroutines.launch

enum class SortAction(val text: Int){
    NAME_ASC(text = R.string.sort_name),
    NAME_DESC(text = R.string.sort_name_desc),
    CALORIES_ASC(text = R.string.sort_calories),
    CALORIES_DESC(text = R.string.sort_calories_desc),
}

@Composable
fun SortAndFilter(vm: RecipesListViewModel){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly){
        Box(contentAlignment = Alignment.Center){
            val sort = LocalContext.current.getString(R.string.sort)
            val sortButtonText by remember { mutableStateOf(sort) }
            var selectedSortFilter by remember { mutableStateOf<SortAction?>(null) }
            var isSortExpanded by remember { mutableStateOf(false)}
            val sortMenuItems = SortAction.values()

            LaunchedEffect(key1 = selectedSortFilter){
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

                var text : String? = selectedSortFilter?.text?.let {
                    LocalContext.current.getString(
                        it
                    )
                }

                Text(
                    text = text ?: sortButtonText,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .wrapContentWidth()
                        .widthIn(min = 40.dp, max = 96.dp)
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
                    }) {
                        Text(text = LocalContext.current.getString(it.text))
                    }
                }
            }
        }

        Box(contentAlignment = Alignment.Center){
            var isFilterExpanded by remember { mutableStateOf(false)}
            Button(onClick = { isFilterExpanded = !isFilterExpanded },
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
                Text(text = LocalContext.current.getString(R.string.filter), color = Color.Black)
            }
            val filterMenuItems = listOf("Тип диеты", "Предосторожности")
            DropdownMenu(
                expanded = isFilterExpanded,
                onDismissRequest = { isFilterExpanded = false }) {
                filterMenuItems.forEach{
                    DropdownMenuItem(onClick = {
                        /*selectedSortFilter = it*/
                        /*sortButtonText =*/
                    }) {
                        Text(text = it)
                    }
                }
            }
        }
    }
}