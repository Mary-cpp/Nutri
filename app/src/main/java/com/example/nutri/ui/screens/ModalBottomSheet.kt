package com.example.nutri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun RecipeBottomSheetContent(
    ingredientName: MutableState<String>,
    ingredientAmount: MutableState<Int>,
    ingredientUnits: MutableState<String>
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Add new ingredient",
                modifier = Modifier.padding(16.dp),
                fontSize = MaterialTheme.typography.h5.fontSize
            )

            OutlinedTextField(modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp),
                value = ingredientName.value,
                shape = NutriShape.mediumRoundedCornerShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onValueChange = { ingredientName.value = it },
                label = { Text("Ingredient name") })

            Row(
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(modifier = Modifier
                    .padding(end = 8.dp)
                    .size(width = 150.dp, height = 64.dp),
                    value = ingredientAmount.value.toString(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Decimal),
                    shape = NutriShape.mediumRoundedCornerShape,
                    colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                    onValueChange = {
                        try {
                            ingredientAmount.value = it.toInt()
                        }
                        catch (_: java.lang.NumberFormatException){

                        }},
                    label = { Text("Amount") })

                DropDownListButton(
                    mutableString = ingredientUnits,
                    color = MaterialTheme.colors.secondaryVariant,
                    menuItems = listOf("g", "ml"))
            }
        }
    }
}

@Composable
fun DropDownListButton(
    mutableString: MutableState<String>,
    menuItems: List<String>,
    shape : RoundedCornerShape = NutriShape.mediumRoundedCornerShape,
    color: Color
){
    val isExpanded = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.padding(top = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { isExpanded.value = true },
            modifier = Modifier
                .padding(start = 8.dp)
                .size(48.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            shape = shape
        ) {
            Text(
                text = mutableString.value,
                fontSize = MaterialTheme.typography.caption.fontSize
            )
        }

        DropdownMenu(expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false }) {
            menuItems.forEach{
                DropdownMenuItem(onClick = { mutableString.value = it }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Preview
@Composable
fun BottomSheetPreview(){

    val name = remember { mutableStateOf("") }
    val amount = remember { mutableStateOf(0) }
    val units = remember { mutableStateOf("") }

    NutriTheme{
        RecipeBottomSheetContent(ingredientName = name,
            ingredientAmount = amount,
            ingredientUnits = units)
    }
}