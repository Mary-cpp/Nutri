package com.example.nutri.ui.screens.bmi

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.bmi.interactor.BmiInteractor
import com.example.nutri.domain.bmi.model.ActivityType
import com.example.nutri.domain.bmi.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val userUseCase: BmiInteractor
) : ViewModel(){

    val userWeight: MutableState<Int> = mutableStateOf(0)
    val userWeightUnit: MutableState<String> = mutableStateOf("kg")
    val userHeight: MutableState<Int> = mutableStateOf(0)
    val userHeightUnit: MutableState<String> = mutableStateOf("sm")
    val userAge: MutableState<Int> = mutableStateOf(0)
    val userSex: MutableState<Char> = mutableStateOf('M')
    val userActivity: MutableState<ActivityType> = mutableStateOf(ActivityType.SEDENTARY)

    val user: MutableState<User?> = mutableStateOf(null)


    fun countPlan() = viewModelScope.launch{
        user.value = User(
            sex = userSex.value,
            height = userHeight.value.toFloat(),
            heightMeasure = userHeightUnit.value,
            weight = userWeight.value.toFloat(),
            weightMeasure = userWeightUnit.value,
            age = userAge.value,
            activityType = userActivity.value
        )

        val plan = userUseCase.countBMI(user.value!!)
        user.value!!.plan = plan

        Log.d("COUNT_PLAN", "ACTIVITY TYPE ${user.value!!.activityType.text}")
        Log.d("COUNT_PLAN", user.value!!.plan!!.kcal.toString())
    }

    fun usePlan() = viewModelScope.launch {
        userUseCase.saveUser(user.value!!)
    }
}