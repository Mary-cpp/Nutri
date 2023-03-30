package com.example.nutri.domain.bmi.interactor

import com.example.nutri.domain.bmi.model.ActivityType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BMIcalculationsTest {

    @Test
    fun stJeorEquationTest(){
        val expected = 1534
        val actual = CountBmiImpl().stJeorEquation(
            weight = 74.0f,
            height = 168.0f,
            age = 19,
            sexValue = -161,
            activityValue = ActivityType.SEDENTARY.index)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun harrisBenedictEquationTest(){
        val expected = 1570
        val actual = CountBmiImpl().harrisBenedictEquation(74.0f,168.0f,19,447.593f, ActivityType.MODERATE.index)

        Assertions.assertEquals(expected, actual)
    }
}