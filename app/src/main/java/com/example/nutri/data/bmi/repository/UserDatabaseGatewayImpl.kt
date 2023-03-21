package com.example.nutri.data.bmi.repository

import com.example.nutri.data.bmi.database.UserDatabase
import com.example.nutri.data.bmi.entity.ActivityTypeEntity
import com.example.nutri.data.bmi.entity.DietPlanEntity
import com.example.nutri.data.bmi.entity.UserEntity
import com.example.nutri.domain.bmi.model.ActivityType
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User

class UserDatabaseGatewayImpl(
    val database: UserDatabase
) : UserDataBaseGateway {
    override suspend fun saveToLocal(user: User): Int {

        database.userDAO()
            .addActivityType(
                mapActivityToEntity(
                    user.activityType))

        val userId = database.userDAO()
            .addUser(
                mapUserToEntity(user))

        database.userDAO()
            .addDietPlan(mapDietPlanToEntity(userId.toInt(), user.plan!!))

        return userId.toInt()
    }

    override suspend fun getUser(id: Int): User {
        TODO("Not yet implemented")
    }

    fun mapActivityToEntity(
        activityType: ActivityType
    ) : ActivityTypeEntity
    = ActivityTypeEntity(text = activityType.text)

    fun mapDietPlanToEntity(
        userId: Int,
        plan: DietPlan
    ) : DietPlanEntity
    = DietPlanEntity(kcal = plan.kcal, userId = userId)

    suspend fun mapUserToEntity(
        user: User
    ) : UserEntity
    = UserEntity(
        height = user.height,
        heightUnit = user.heightMeasure,
        weight = user.weight,
        weightUnit = user.weightMeasure,
        age = user.age,
        sex = user.sex,
        activityTypeId = database.userDAO().getActivityTypeId(user.activityType.text)
    )
}