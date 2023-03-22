package com.example.nutri.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.nutri.data.bmi.entity.*

@Dao
interface UserDAO {

    @Insert
    suspend fun addActivityType(type: ActivityTypeEntity)

    @Query("SELECT * FROM activity_types WHERE id = :id")
    suspend fun getActivityTypeById(id: Int) : ActivityTypeEntity

    @Insert
    suspend fun addDietPlan(plan: DietPlanEntity)

    @Query("SELECT * FROM diet_plans WHERE user_id = :userId")
    suspend fun getDietPlanByUser(userId: Int) : DietPlanEntity

    @Insert
    suspend fun addUser(userEntity: UserEntity) : Long

    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUser(userId: Int) : UserEntity

    @Query("SELECT id FROM activity_types WHERE text = :text")
    suspend fun getActivityTypeId(text: String): Int
}