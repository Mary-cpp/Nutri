package com.example.nutri.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nutri.domain.statistics.Water
import java.util.*

@Dao
interface WaterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWater(data: Water)

    @Query("SELECT * FROM water where date = :date")
    suspend fun getWaterInfoByDate(date: Date) : Water?
}