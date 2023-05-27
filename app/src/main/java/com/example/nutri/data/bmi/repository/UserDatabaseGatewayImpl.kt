package com.example.nutri.data.bmi.repository

import android.content.Context
import android.util.Log
import com.example.nutri.core.ResultState
import com.example.nutri.data.bmi.entity.ActivityTypeEntity
import com.example.nutri.data.bmi.entity.DietPlanEntity
import com.example.nutri.data.bmi.entity.UserEntity
import com.example.nutri.data.database.RecipeDatabase
import com.example.nutri.domain.bmi.UserDataBaseGateway
import com.example.nutri.domain.bmi.model.ActivityType
import com.example.nutri.domain.bmi.model.DietPlan
import com.example.nutri.domain.bmi.model.User
import com.example.nutri.ui.screens.my_recipes.TAG
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class UserDatabaseGatewayImpl @Inject constructor(
    val database: RecipeDatabase,
    @ApplicationContext val context: Context,
) : UserDataBaseGateway {

    override suspend fun saveUserInfo(user: User): String {
        val userId: String = UUID.randomUUID().toString()
        withContext(Dispatchers.IO){
            val activity = context.resources.getString(user.activityType.text)
            database.userDAO().addActivityType(ActivityTypeEntity(text = activity, id = null))
            database.userDAO().addUserInfo(
                activityType = ActivityTypeEntity(text = activity, id = null),
                user = mapUserToEntity(user, userId),
                dietPlan = DietPlanEntity(id = null, kcal = user.plan!!.kcal, userId = userId)
            )
        }
        return userId
    }

    override suspend fun getLastUserInfo(): Flow<ResultState<User>> {

        return try {
            database.userDAO().getLastUser()
                .transform { dbUser ->
                    dbUser?.let{
                        var activityType = ActivityType.LIGHT
                        ActivityType.values().forEach{ actType ->
                            val type = context.resources.getString(actType.text)
                            if (it.activityTypeEntity.text == type) activityType = actType
                        }
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
                                activityType = activityType,
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
    { 
        val activity = context.resources.getString(user.activityType.text)
        Log.i(TAG, activity)
        return UserEntity(
        id = id,
        height = user.height,
        heightUnit = user.heightMeasure,
        weight = user.weight,
        weightUnit = user.weightMeasure,
        age = user.age,
        sex = user.sex,
        activityTypeId = database.userDAO().getActivityTypeId(activity)
    )
    }
    
}

