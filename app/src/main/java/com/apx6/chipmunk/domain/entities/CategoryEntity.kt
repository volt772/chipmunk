package com.apx6.chipmunk.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.chipmunk.domain.constants.CmdEntityTags

@Entity(
    tableName = "category",
    foreignKeys = [ForeignKey(entity = UserEntity::class, parentColumns = ["id"], childColumns = ["uid"], onDelete = CASCADE)],
    indices = [Index(value = ["id"], unique = true)]
)
data class CategoryEntity(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = CmdEntityTags.ID)
    val id: Long = 0,

    @field:ColumnInfo(name = CmdEntityTags.UID)
    val uid: Long,

    @field:ColumnInfo(name = CmdEntityTags.NAME)
    val name: String

)