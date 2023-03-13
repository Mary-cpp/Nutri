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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun RecipeBottomSheetContent(
    ingredientName: MutableState<String>,
    ingredientAmount: MutableState<Int>,
    ingredientUnits: MutableState<String>
) {

    val isExpanded = remember { mutableStateOf(false) }

    Surface(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Add new ingredient",
                modifier = Modifier.padding(16.dp),
                fontSize = MaterialTheme.typography.h5.fontSize)

            OutlinedTextField(modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp),
                value = ingredientName.value,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                onValueChange = { ingredientName.value = it },
                label = { Text("Ingredient name") })

            Row(modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically){
                OutlinedTextField(modifier = Modifier
                    .padding(end = 8.dp)
                    .size(width = 150.dp, height = 64.dp),
                    value = ingredientAmount.value.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(Color.Black),
                    onValueChange = { ingredientAmount.value = it.toInt() },
                    label = { Text("Amount") })

                Box (modifier = Modifier.padding(top = 8.dp),
                    contentAlignment = Alignment.Center) {
                    Button(onClick = { isExpanded.value = true },
                        modifier = Modifier
                            .padding(8.dp)
                            .size(48.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = ingredientUnits.value, fontSize = MaterialTheme.typography.caption.fontSize)
                    }

                    DropdownMenu(expanded = isExpanded.value,
                        onDismissRequest = { isExpanded.value = false }) {
                        DropdownMenuItem(onClick = { ingredientUnits.value = "g" }) { Text(text = "g") }
                        DropdownMenuItem(onClick = { ingredientUnits.value = "ml"  }) { Text(text = "ml") }
                    }
                }
            }
        }
    }
}