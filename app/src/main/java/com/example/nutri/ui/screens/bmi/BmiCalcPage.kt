package com.example.nutri.ui.screens.bmi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nutri.domain.bmi.model.ActivityType
import com.example.nutri.ui.screens.DropDownListButton
import com.example.nutri.ui.screens.bmi.composables.DietResultCard
import com.example.nutri.ui.screens.bmi.composables.TrackUser
import com.example.nutri.ui.screens.common.TopBar
import com.example.nutri.ui.theme.NutriShape
import com.example.nutri.ui.theme.NutriTheme

@Composable
fun BmiPage(
    vm: BmiViewModel = hiltViewModel()){

    Scaffold(modifier = Modifier.fillMaxSize(),
        contentColor = Color.White,
        topBar = { TopBar("BMI Calculator") },
        content = {
            Surface(
                Modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colors.background
            ){
                Column (Modifier.verticalScroll(rememberScrollState(), true)){
                    vm.user.value?.plan?.let { plan ->
                        DietResultCard(plan = plan)
                    }

                    BmiCalcCard(vm = vm)
                }
            }
        }
    )
}

@Composable
fun BmiCalcCard(vm : BmiViewModel){

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        shape = NutriShape.mediumRoundedCornerShape,
        backgroundColor = MaterialTheme.colors.surface){

        val weight = vm.userWeight
        val height = vm.userHeight
        val age = vm.userAge

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

                Button(onClick = { vm.userSex.value = 'F'},
                    modifier = Modifier.size(width = 136.dp, height = 176.dp),
                    elevation = ButtonDefaults.elevation(6.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)
                ) {

                    Text (text = "F",
                        fontSize = MaterialTheme.typography.h3.fontSize)
                }

                Button(onClick = { vm.userSex.value = 'M'},
                    modifier = Modifier.size(width = 136.dp, height = 176.dp),
                    elevation = ButtonDefaults.elevation(6.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondaryVariant)
                ) {

                    Text (text = "M",
                        fontSize = MaterialTheme.typography.h3.fontSize)
                }
            }

            Row(modifier = Modifier.padding(top = 16.dp, start = 8.dp, bottom = 8.dp)){

                val weightUnits = vm.userWeightUnit

                OutlinedTextField(
                    value = weight.value.toString(),
                    onValueChange = {
                        if (it.isNotEmpty()){
                            weight.value = it.toInt()}
                        },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = NutriShape.smallRoundedCornerShape,
                    colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.primary),
                    modifier = Modifier.size(width = 136.dp, height = 64.dp),
                    label = { Text( "Weight")}
                )

                DropDownListButton(mutableString = weightUnits,
                    color = MaterialTheme.colors.primary,
                    shape = NutriShape.smallRoundCornerShape,
                    menuItems = listOf("kg", "lbs"),
                    buttonSize = 48)
            }

            Row(modifier = Modifier.padding(8.dp)){

                val heightUnits = vm.userHeightUnit

                OutlinedTextField(
                    value = height.value.toString(),
                    onValueChange = {
                        if (it.isNotEmpty()){
                        height.value = it.toInt()}
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = NutriShape.smallRoundedCornerShape,
                    colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.primary),
                    modifier = Modifier.size(width = 136.dp, height = 64.dp),
                    label = { Text( "Height")}
                )

                DropDownListButton(mutableString = heightUnits,
                    color = MaterialTheme.colors.primary,
                    shape = NutriShape.smallRoundCornerShape,
                    menuItems = listOf("m", "ft"),
                    buttonSize = 48)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween){



                OutlinedTextField(
                    value = age.value.toString(),
                    onValueChange = {
                        if (it.isNotEmpty()){
                            age.value = it.toInt()}
                            },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = NutriShape.smallRoundedCornerShape,
                    colors = TextFieldDefaults.outlinedTextFieldColors(MaterialTheme.colors.primary),
                    modifier = Modifier.size(width = 88.dp, height = 64.dp),
                    label = { Text( "Age")}
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                val activityType = vm.userActivity

                Text(text = "Activity type:", fontSize = MaterialTheme.typography.subtitle2.fontSize)

                ActivityDropDownListButton(
                    mutableString = remember {
                        mutableStateOf( activityType.value.text)
                    },
                    color = MaterialTheme.colors.background,
                    shape = NutriShape.smallRoundCornerShape,
                    menuItems = ActivityType.entries,
                    buttonSize = 150,
                    activity = activityType
                )
            }

            Button(
                onClick = {
                    vm.countPlan() },
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

            vm.user.value?.plan?.let {
                TrackUser(context = context, vm = vm)
            }
        }
    }
}

@Preview
@Composable
fun BmiPagePreview(){
    NutriTheme {
        BmiPage(
            hiltViewModel())
    }
}