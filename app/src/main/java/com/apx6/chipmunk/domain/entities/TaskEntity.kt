package com.apx6.chipmunk.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.chipmunk.domain.constants.CmdEntityTags

@Entity(
    tableName = "task",
    foreignKeys = [
        ForeignKey(entity = UserEntity::class, parentColumns = ["id"], childColumns = ["uid"], onDelete = CASCADE),
        ForeignKey(entity = CategoryEntity::class, parentColumns = ["id"], childColumns = ["cid"], onDelete = CASCADE),
    ],
    indices = [Index(value = ["id"], unique = true)]
)
data class TaskEntity(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = CmdEntityTags.ID)
    val id: Long = 0,

    @field:ColumnInfo(name = CmdEntityTags.CID)
    val cid: Long,

    @field:ColumnInfo(name = CmdEntityTags.UID)
    val uid: Long,

    @field:ColumnInfo(name = CmdEntityTags.TITLE)
    val title: String,

    @field:ColumnInfo(name = CmdEntityTags.MEMO)
    val memo: String,

    @field:ColumnInfo(name = CmdEntityTags.START_DATE)
    val startDate: Long,

    @field:ColumnInfo(name = CmdEntityTags.END_DATE)
    val endDate: Long

)