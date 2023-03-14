package com.example.nutri.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.ui.navigation.BottomNavigationBar
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun BmiPage(navController : NavController){

    Scaffold(modifier = Modifier.fillMaxSize(),
        contentColor = Color.White,
        bottomBar = { BottomNavigationBar(navController = navController) },
        topBar = { TopBar(topBarText = "BMI Calculator") },
        content = {

            Column(Modifier.background(Color.White)) {

                Card(modifier = Modifier.padding(it)) {
                    BottomSheetContent()
                }
            }
        }
    )
}

@Composable
fun BottomSheetContent(){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        shape = NutriShape.mediumRoundedCornerShape,
        backgroundColor = MaterialTheme.colors.surface){

        val weight = remember { mutableStateOf(0) }
        val height = remember { mutableStateOf(0) }
        val age = remember { mutableStateOf(0) }

        Column(modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.SpaceEvenly){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Choose your gender",
                fontSize = MaterialTheme.typography.h5.fontSize,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly){

                Button(onClick = {},
                    modifier = Modifier.size(width = 136.dp, height = 176.dp),
                    elevation = ButtonDefaults.elevation(6.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)
                ) {

                    Text (text = "F",
                        fontSize = MaterialTheme.typography.h3.fontSize)
                }

                Button(onClick = {},
                    modifier = Modifier.size(width = 136.dp, height = 176.dp),
                    elevation = ButtonDefaults.elevation(6.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondaryVariant)
                ) {

                    Text (text = "M",
                        fontSize = MaterialTheme.typography.h3.fontSize)
                }
            }

            Row(modifier = Modifier){

                val weightUnits = remember { mutableStateOf("kg") }

                OutlinedTextField(
                    value = weight.value.toString(),
                    onValueChange = { weight.value = it.toInt()},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = NutriShape.smallRoundedCornerShape,
                    colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.primary),
                    modifier = Modifier.size(width = 136.dp, height = 64.dp),
                    label = { Text( "Weight")}
                )

                DropDownListButton(mutableString = weightUnits,
                    color = MaterialTheme.colors.primary,
                    shape = NutriShape.smallRoundCornerShape,
                    menuItems = listOf("kg", "lbs"))
            }

            Row(modifier = Modifier){

                val heightUnits = remember { mutableStateOf("m") }

                OutlinedTextField(
                    value = height.value.toString(),
                    onValueChange = { height.value = it.toInt()},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = NutriShape.smallRoundedCornerShape,
                    colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.primary),
                    modifier = Modifier.size(width = 136.dp, height = 64.dp),
                    label = { Text( "Height")}
                )

                DropDownListButton(mutableString = heightUnits,
                    color = MaterialTheme.colors.primary,
                    shape = NutriShape.smallRoundCornerShape,
                    menuItems = listOf("m", "ft"))
            }
            Row(modifier = Modifier){

                OutlinedTextField(
                    value = age.value.toString(),
                    onValueChange = { age.value = it.toInt()},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = NutriShape.smallRoundedCornerShape,
                    colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.primary),
                    modifier = Modifier.size(width = 88.dp, height = 64.dp),
                    label = { Text( "Age")}
                )
            }



            Button(
                onClick = { /*TODO*/ },
                elevation = ButtonDefaults.elevation(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Calculate",
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = MaterialTheme.typography.subtitle2.fontSize)
            }
        }


    }
}

@Preview
@Composable
fun BmiPagePreview(){
    NutriTheme {
        BmiPage(rememberNavController())
    }
}