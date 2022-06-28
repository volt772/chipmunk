package com.apx6.chipmunk.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.chipmunk.domain.constants.CmdEntityTags

@Entity(
    tableName = "notification",
    foreignKeys = [ForeignKey(entity = TaskEntity::class, parentColumns = ["id"], childColumns = ["tid"], onDelete = CASCADE)],
    indices = [Index(value = ["id"], unique = true)]
)
data class NotificationEntity(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = CmdEntityTags.ID)
    val id: Long = 0,

    @field:ColumnInfo(name = CmdEntityTags.TID)
    val tid: Long,

    @field:ColumnInfo(name = CmdEntityTags.PERIOD)
    val period: Long

)