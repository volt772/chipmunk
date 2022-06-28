package com.apx6.chipmunk.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apx6.chipmunk.domain.constants.CmdEntityTags

@Entity(
    tableName = "user",
    indices = [Index(value = ["id"], unique = true)]
)
data class UserEntity(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = CmdEntityTags.ID)
    val id: Long = 0,

    @field:ColumnInfo(name = CmdEntityTags.ACCOUNT)
    val account: String,

    @field:ColumnInfo(name = CmdEntityTags.NICKNAME)
    val nickName: String,

    @field:ColumnInfo(name = CmdEntityTags.EMAIL)
    val email: String,

    @field:ColumnInfo(name = CmdEntityTags.REG_DATE)
    val regDate: Long,

    @field:ColumnInfo(name = CmdEntityTags.PROFILE_THUMBNAIL)
    val profileThumbnail: String,

    @field:ColumnInfo(name = CmdEntityTags.F_TOKEN)
    val fToken: String

)