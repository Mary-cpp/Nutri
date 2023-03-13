package com.example.nutri.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun BmiPage(navController : NavController){

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {BottomNavigationBar(navController = navController)},
        topBar = { TopBar(topBarText = "BMI Calculator") },
        content = {
            Card(modifier = Modifier.padding(it)) {
                BottomSheetContent()
            }
        })
}

@Composable
fun BottomSheetContent(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color =  MaterialTheme.colors.background){

        Column(modifier = Modifier.padding(24.dp)){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Choose your gender",
                fontSize = MaterialTheme.typography.h4.fontSize,
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