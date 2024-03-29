package com.example.nutri.domain.bmi.interactor

import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User

class CountBmiImpl: CountBMI {
    override fun invoke(user: User): DietPlan = DietPlan(kcal = avg(user))

    private fun avg(user: User) : Int
    {
        var bmi1 = 0; var bmi2 = 0

        if (user.sex == 'F'){
            bmi1 = stJeorEquation(
                weight = user.weight,
                height = user.height,
                age = user.age,
                sexValue = -161,
                activityValue = user.activityType.index)
            bmi2 = harrisBenedictEquation(
                weight = user.weight,
                height = user.height,
                age = user.age,
                sexValue = 447.593f,
                activityValue = user.activityType.index
            )
        }
        if (user.sex == 'M'){
            bmi1 = stJeorEquation(
                weight = user.weight,
                height = user.height,
                age = user.age,
                sexValue = 5,
                activityValue = user.activityType.index)
            bmi2 = harrisBenedictEquation(
                weight = user.weight,
                height = user.height,
                age = user.age,
                sexValue = 88.362f,
                activityValue = user.activityType.index
            )
        }
        return (bmi1+bmi2)/2
    }

    fun stJeorEquation(
        weight: Float,
        height: Float,
        age: Int,
        sexValue: Int,
        activityValue:Float
    ) : Int
    = ((10*weight+6.25*height-5*age+sexValue)*activityValue).toInt()

    fun harrisBenedictEquation(
        weight: Float,
        height: Float,
        age: Int,
        sexValue: Float,
        activityValue:Float
    ) : Int
    = ((9.247*weight+3.098*height-4.330*age +sexValue)*activityValue).toInt()
}