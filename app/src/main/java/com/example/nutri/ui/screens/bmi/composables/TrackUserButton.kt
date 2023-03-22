package com.example.nutri.ui.screens.bmi.composables

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.nutri.ui.screens.bmi.BmiViewModel

@Composable
fun TrackUser(
    context: Context,
    vm: BmiViewModel
){
    Text(text = "Use this value to track your daily meals?",
        modifier = Modifier.clickable{

            vm.apply {
                usePlan()
            }

            val sp = context.getSharedPreferences("bmi", Context.MODE_PRIVATE)

            vm.user.value!!.id?.let {
                with( sp.edit()){
                    putInt("userId", it)
                }
            }
        })
}