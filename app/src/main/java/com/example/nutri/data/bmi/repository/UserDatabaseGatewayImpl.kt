package com.example.nutri.data.bmi.repository

import android.util.Log
import com.example.nutri.data.bmi.entity.ActivityTypeEntity
import com.example.nutri.data.bmi.entity.DietPlanEntity
import com.example.nutri.data.bmi.entity.UserEntity
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.domain.bmi.model.ActivityType
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDatabaseGatewayImpl(
    val database: RecipeDatabase
) : UserDataBaseGateway {

    private val TAG = "UserDatabaseGatewayImpl"
    override suspend fun saveToLocal(user: User): Int {

        var userId: Long

        withContext(Dispatchers.IO){

            // add information about activity type
            database.userDAO()
                .addActivityType(
                    mapActivityToEntity(
                        user.activityType))

            // add user and save his id
            userId = database.userDAO()
                .addUser(
                    mapUserToEntity(user))

            // add dietPlan
            database.userDAO()
                .addDietPlan(mapDietPlanToEntity(userId.toInt(), user.plan!!))
        }

        return userId.toInt()
    }

    override suspend fun getUser(id: Int): User {

        val user = mapEntityToUser(id)

        Log.d(TAG, "User: $user")
        return user
    }

    override suspend fun getLastUser(): User? {
        val user = mapEntityToUser()

        if (user != null) {
            Log.d(TAG, "User: ${user.id}")
        }
        else{Log.d(TAG, "User not found")}
        return user
    }

    private fun mapActivityToEntity(
        activityType: ActivityType
    ) : ActivityTypeEntity
    = ActivityTypeEntity(text = activityType.text, id = null)

    private fun mapDietPlanToEntity(
        userId: Int,
        plan: DietPlan
    ) : DietPlanEntity
    = DietPlanEntity(kcal = plan.kcal, userId = userId, id = null)

    private suspend fun mapUserToEntity(
        user: User
    ) : UserEntity
    = UserEntity(
        id = null,
        height = user.height,
        heightUnit = user.heightMeasure,
        weight = user.weight,
        weightUnit = user.weightMeasure,
        age = user.age,
        sex = user.sex,
        activityTypeId = database.userDAO().getActivityTypeId(user.activityType.text)
    )

    private suspend fun mapEntityToUser(
        id: Int
    ) : User{

        val user = database.userDAO().getUser(id)
        val userPlan = database.userDAO().getDietPlanByUser(id)
        val activityType = database.userDAO().getActivityTypeById(user.activityTypeId)

        return User(
            height = user.height,
            heightMeasure = user.heightUnit,
            weight = user.weight,
            weightMeasure = user.weightUnit,
            age = user.age,
            sex = user.sex,
            plan = DietPlan(userPlan.kcal),
            activityType = ActivityType.valueOf(activityType.text)
        )
    }

    private suspend fun mapEntityToUser() : User?{

        val user = database.userDAO().getLastUser()
        user?.let{
            val userPlan = database.userDAO().getDietPlanByUser(it.id!!)
            val activityType = database.userDAO().getActivityTypeById(user.activityTypeId)

            return User(
                height = user.height,
                heightMeasure = user.heightUnit,
                weight = user.weight,
                weightMeasure = user.weightUnit,
                age = user.age,
                sex = user.sex,
                plan = DietPlan(userPlan.kcal),
                activityType = ActivityType.valueOf(activityType.text)
            )
        }
        return null
    }
}

