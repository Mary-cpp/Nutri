package com.example.nutri.data.bmi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nutri.data.bmi.database.dao.UserDAO
import com.example.nutri.data.bmi.entity.*

@Database(
    entities =
    [
        ActivityTypeEntity::class,
        DietPlanEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false)
abstract class UserDatabase : RoomDatabase()
{

    abstract fun userDAO() : UserDAO

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    UserDatabase :: class.java,
                    "recipe_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}