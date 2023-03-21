package com.example.nutri.domain.bmi.interactor

import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.model.User

class CountBmiImpl: CountBMI {
    override fun invoke(user: User): DietPlan {
        return DietPlan(kcal = avg(user))
    }

    fun avg(
        user: User) : Int{

        var bmi1 = 0; var bmi2 = 0

        if (user.sex == 'F'){
            bmi1 = stJeorEquation(
                weight = user.weight,
                height = user.height,
                age = user.age,
                sexValue = -161)
            bmi2 = harrisBenedictEquation(
                weight = user.weight,
                height = user.height,
                age = user.age,
                sexValue = 447.593f
            )
        }
        if (user.sex == 'M'){
            bmi1 = stJeorEquation(
                weight = user.weight,
                height = user.height,
                age = user.age,
                sexValue = 5)
            bmi2 = harrisBenedictEquation(
                weight = user.weight,
                height = user.height,
                age = user.age,
                sexValue = 88.362f
            )
        }

        return (bmi1+bmi2)/2
    }

    fun stJeorEquation(
        weight: Float,
        height: Float,
        age: Int,
        sexValue: Int
    ) : Int{

        return (10*weight+6.25*height-5*age+sexValue).toInt()
    }

    fun harrisBenedictEquation(
        weight: Float,
        height: Float,
        age: Int,
        sexValue: Float
    ) : Int{

        return (9.247*weight+3.098*height-4.330*age +sexValue).toInt()
    }
}