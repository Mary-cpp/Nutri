package com.example.nutri.ui.viewmodel.bmi

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutri.domain.bmi.interactor.BmiInteractor
import com.example.nutri.domain.bmi.model.ExerciseType
import com.example.nutri.domain.bmi.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val userUseCase: BmiInteractor
) : ViewModel(){

    val userWeight: MutableState<Float> = mutableStateOf(0f)
    val userWeightUnit: MutableState<String> = mutableStateOf("kg")
    val userHeight: MutableState<Float> = mutableStateOf(0f)
    val userHeightUnit: MutableState<String> = mutableStateOf("sm")
    val userAge: MutableState<Int> = mutableStateOf(0)
    val userSex: MutableState<Char> = mutableStateOf('M')
    val userActivity: MutableState<ExerciseType> = mutableStateOf(ExerciseType.SEDENTARY)

    private val user: MutableState<User?> = mutableStateOf(null)


    fun countPlan() = viewModelScope.launch{
        user.value = User(
            sex = userSex.value,
            height = userHeight.value,
            heightMeasure = userHeightUnit.value,
            weight = userWeight.value,
            weightMeasure = userWeightUnit.value,
            age = userAge.value,
            exerciseType = userActivity.value
        )

        val plan = userUseCase.countBMI(user.value!!)

        user.value!!.plan = plan
    }
}