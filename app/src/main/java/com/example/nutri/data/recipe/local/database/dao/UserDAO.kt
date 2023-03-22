package com.example.nutri.data.recipe.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nutri.data.bmi.entity.*

@Dao
interface UserDAO {

    @Insert
    suspend fun addActivityType(type: ActivityTypeEntity)

    @Insert
    suspend fun addDietPlan(plan: DietPlanEntity)

    @Insert
    suspend fun addUser(userEntity: UserEntity) : Long

    @Query("SELECT id FROM activity_types WHERE text = :text")
    suspend fun getActivityTypeId(text: String): Int
}