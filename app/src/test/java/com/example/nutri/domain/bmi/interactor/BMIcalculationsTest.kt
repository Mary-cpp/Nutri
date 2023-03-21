package com.example.nutri.domain.bmi.interactor

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BMIcalculationsTest {

    @Test
    fun stJeorEquationTest(){
        val expected = 1534
        val actual = CountBmiImpl().stJeorEquation(74.0f,168.0f,19,-161)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun harrisBenedictEquationTest(){
        val expected = 1570
        val actual = CountBmiImpl().harrisBenedictEquation(74.0f,168.0f,19,447.593f)

        Assertions.assertEquals(expected, actual)
    }
}