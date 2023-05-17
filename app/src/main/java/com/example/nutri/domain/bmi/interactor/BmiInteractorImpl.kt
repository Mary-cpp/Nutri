package com.example.nutri.domain.bmi.interactor

import android.util.Log
import com.example.nutri.core.ResultState
import com.example.nutri.domain.bmi.UserDataBaseGateway
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User
import com.example.nutri.domain.statistics.model.Meal
import kotlinx.coroutines.flow.Flow

class BmiInteractorImpl(
    val counter: CountBMI,
    val saver: UserDataBaseGateway
) : BmiInteractor {
    override fun countBMI(user: User) : DietPlan {
        return counter.invoke(user)
    }

    override fun countNutritionData(list: List<Meal>): DietPlan {
        var todayCalories = 0
        var todayProteins = 0f
        var todayCarbs = 0f
        var todayFats = 0f
        list.forEach{ meal->
            if(meal.recipes.isNotEmpty()){
                meal.recipes.forEach {
                    Log.w(this::class.java.simpleName, it.toString())
                    todayCalories += it.calories?.toInt() ?: 0
                    todayProteins += it.totalNutrients!!["PROCNT"]?.quantity?.toFloat() ?: 0f
                    todayCarbs += it.totalNutrients["CHOCDF"]?.quantity?.toFloat() ?: 0f
                    todayFats += it.totalNutrients["FAT"]?.quantity?.toFloat() ?: 0f
                }
            }
        }
        Log.i(this::class.java.simpleName, "Today's calories $todayCalories")
        return DietPlan(todayCalories, todayProteins, todayCarbs, todayFats)
    }

    override suspend fun saveUser(user: User) {
        saver.saveToLocal(user)
    }

    override suspend fun getCurrentUser(): Flow<ResultState<User>> {
        return saver.getLastUser()
    }
}