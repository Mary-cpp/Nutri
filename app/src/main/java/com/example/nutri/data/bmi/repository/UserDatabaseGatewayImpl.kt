package com.example.nutri.data.bmi.repository

import com.example.nutri.core.ResultState
import com.example.nutri.data.bmi.entity.ActivityTypeEntity
import com.example.nutri.data.bmi.entity.DietPlanEntity
import com.example.nutri.data.bmi.entity.UserEntity
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.domain.bmi.UserDataBaseGateway
import com.example.nutri.domain.bmi.model.ActivityType
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.util.*

class UserDatabaseGatewayImpl(
    val database: RecipeDatabase
) : UserDataBaseGateway {

    override suspend fun saveToLocal(user: User): String {
        val userId: String = UUID.randomUUID().toString()
        withContext(Dispatchers.IO){
            database.userDAO().addUserInfo(
                activityType = ActivityTypeEntity(text = user.activityType.text, id = null),
                user = mapUserToEntity(user, userId),
                dietPlan = DietPlanEntity(id = null, kcal = user.plan!!.kcal, userId = userId)
            )
        }
        return userId
    }

    override suspend fun getLastUser(): Flow<ResultState<User>> {

        return try {
            database.userDAO().getLastUser()
                .transform { dbUser ->
                    dbUser?.let{
                        emit(ResultState.Success(
                            User(
                                id = it.userEntity.id,
                                sex = it.userEntity.sex,
                                height = it.userEntity.height,
                                heightMeasure = it.userEntity.heightUnit,
                                weight = it.userEntity.weight,
                                weightMeasure = it.userEntity.weightUnit,
                                age = it.userEntity.age,
                                plan = DietPlan(kcal = it.dietPlanEntity.kcal),
                                activityType = ActivityType.valueOf(it.activityTypeEntity.text),
                            )
                        ))
                    }
                }.flowOn(Dispatchers.IO)
        } catch (e: NullPointerException){
            flowOf(ResultState.Error(exception = e))
        }
    }

    private suspend fun mapUserToEntity(
        user: User,
        id : String,
    ) : UserEntity
    = UserEntity(
        id = id,
        height = user.height,
        heightUnit = user.heightMeasure,
        weight = user.weight,
        weightUnit = user.weightMeasure,
        age = user.age,
        sex = user.sex,
        activityTypeId = database.userDAO().getActivityTypeId(user.activityType.text)
    )
}

