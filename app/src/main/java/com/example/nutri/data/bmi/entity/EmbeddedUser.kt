package com.example.nutri.data.bmi.entity

import androidx.room.Embedded
import androidx.room.Relation

class EmbeddedUser(
    @Embedded
    val userEntity: UserEntity,
    @Relation(
        entity = DietPlanEntity::class,
        entityColumn = "user_id",
        parentColumn = "id",
    )
    val dietPlanEntity: DietPlanEntity,
    @Relation(
        entity = ActivityTypeEntity::class,
        entityColumn = "id",
        parentColumn = "activity_type_id",
    )
    val activityTypeEntity: ActivityTypeEntity,
)