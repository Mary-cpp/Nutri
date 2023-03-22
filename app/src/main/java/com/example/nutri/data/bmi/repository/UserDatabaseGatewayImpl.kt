package com.example.nutri.data.bmi.repository

import com.example.nutri.data.bmi.entity.ActivityTypeEntity
import com.example.nutri.data.bmi.entity.DietPlanEntity
import com.example.nutri.data.bmi.entity.UserEntity
import com.example.nutri.data.recipe.local.database.RecipeDatabase
import com.example.nutri.domain.bmi.model.ActivityType
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDatabaseGatewayImpl(
    val database: RecipeDatabase
) : UserDataBaseGateway {
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

            // add dietplan
            database.userDAO()
                .addDietPlan(mapDietPlanToEntity(userId.toInt(), user.plan!!))
        }

        return userId.toInt()
    }

    override suspend fun getUser(id: Int): User {
        TODO("Not yet implemented")
    }

    fun mapActivityToEntity(
        activityType: ActivityType
    ) : ActivityTypeEntity
    = ActivityTypeEntity(text = activityType.text, id = null)

    fun mapDietPlanToEntity(
        userId: Int,
        plan: DietPlan
    ) : DietPlanEntity
    = DietPlanEntity(kcal = plan.kcal, userId = userId, id = null)

    suspend fun mapUserToEntity(
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
}