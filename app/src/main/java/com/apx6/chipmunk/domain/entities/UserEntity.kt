package com.apx6.chipmunk.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apx6.chipmunk.domain.constants.CmdEntityTags
import com.apx6.chipmunk.domain.entities.UserEntity.Companion.TABLE_USER

@Entity(
    tableName = TABLE_USER,
    indices = [Index(value = ["id"], unique = true)]
)
data class UserEntity(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = CmdEntityTags.ID)
    var id: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.ACCOUNT)
    var account: String?= "",

    @field:ColumnInfo(name = CmdEntityTags.NICKNAME)
    var nickName: String?= "",

    @field:ColumnInfo(name = CmdEntityTags.EMAIL)
    var email: String?= "",

    @field:ColumnInfo(name = CmdEntityTags.REG_DATE)
    var regDate: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.PROFILE_THUMBNAIL)
    var profileThumbnail: String?= "",

    @field:ColumnInfo(name = CmdEntityTags.F_TOKEN)
    var fToken: String?= ""

) {

    companion object {
        const val TABLE_USER = "user"
    }

}