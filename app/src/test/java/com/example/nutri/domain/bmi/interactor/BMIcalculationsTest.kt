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
}