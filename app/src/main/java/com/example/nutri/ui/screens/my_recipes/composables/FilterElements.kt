package com.example.nutri.ui.screens.my_recipes.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutri.domain.recipes.model.Cautions
import com.example.nutri.domain.recipes.model.DietFilter
import com.example.nutri.domain.recipes.model.FilterActions
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun FilterCategories(
    modifier: Modifier = Modifier,
    isExpanded: MutableState<Boolean>,
    onFiltersClear: () -> Unit,
    onFilterSelected: (FilterActions) -> Unit,
){
    Box(
        modifier = modifier.wrapContentSize(),
    ) {
        DropdownMenu(expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = !isExpanded.value }
        ) {
            DropdownMenuItem(onClick = {}) {
                Text("Диета:", textDecoration = TextDecoration.Underline)
            }
            DietFilter.values().forEach {
                DropdownMenuItem(onClick = { onFilterSelected.invoke(FilterActions.Diet(it)) }) {
                    Text(text = it.name)
                }
            }
            Cautions.values().forEach {
                DropdownMenuItem(onClick = { onFilterSelected.invoke(FilterActions.Caution(it)) }) {
                    Text(text = it.name)
                }
            }
            DropdownMenuItem(onClick = {onFiltersClear.invoke()}) {
                Text("Очистить фильтр", fontStyle = FontStyle.Italic)
            }
        }
    }
}

@Composable
fun FilterCategoriesTest(
){
    val isMenuExpanded = remember { mutableStateOf(true) }
    DropdownMenu(expanded = isMenuExpanded.value,
        onDismissRequest = { isMenuExpanded.value = !isMenuExpanded.value }
    ) {
        DropdownMenuItem(onClick = {}) {
            Text("Диета:")
        }
        DietFilter.values().forEach {
            DropdownMenuItem(onClick = {  }) {
                Text(text = it.name)
            }
        }
        DropdownMenuItem(onClick = {}) {
            Text("Предосторожности:")
        }
        Cautions.values().forEach {
            DropdownMenuItem(onClick = {  }) {
                Text(text = it.name)
            }
        }
    }
}

@Preview
@Composable
fun FilterCategoriesPreview(){
    NutriTheme {
        FilterCategoriesTest()
    }
}
