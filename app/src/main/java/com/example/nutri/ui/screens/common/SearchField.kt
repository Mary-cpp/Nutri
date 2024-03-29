package com.example.nutri.ui.screens.common

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.R
import com.example.nutri.ui.screens.search.SearchViewModel
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun SearchTextField(
    vm: SearchViewModel? = null){

    val searchParameter = remember{ mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(searchParameter.value ){
        if(searchParameter.value.text != ""){
            vm?.getRecipes(searchParameter.value.text)
        }
    }
    OutlinedTextField(
        modifier = Modifier.size(width = 304.dp, height = 64.dp),
        value = searchParameter.value,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
        onValueChange = {
            searchParameter.value = it
        },
        singleLine = true,
        trailingIcon = {
            Icon(imageVector = ImageVector
                .vectorResource(id = R.drawable.search48px),
                contentDescription = "SearchIcon",
                modifier = Modifier.size(32.dp)) },
        label = { Text("Search for recipes") }
    )
}

@Composable
fun SearchFieldTest(){

    val searchParameter = remember{ mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.size(width = 304.dp, height = 64.dp),
        value = searchParameter.value,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
        onValueChange = {
            searchParameter.value = it
        },
        trailingIcon = {
            Icon(imageVector = ImageVector
                .vectorResource(id = R.drawable.search48px),
                contentDescription = "SearchIcon",
                modifier = Modifier.size(32.dp)) },
        label = { Text("Search for recipes") })
}

@Preview
@Composable
fun SearchFiledTestPreview(){
    NutriTheme {
        SearchFieldTest()
    }
}