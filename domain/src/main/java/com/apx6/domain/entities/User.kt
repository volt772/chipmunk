package com.apx6.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apx6.domain.constants.CmdEntityTags
import com.apx6.domain.entities.User.Companion.TABLE_USER

@Entity(
    tableName = TABLE_USER,
    indices = [
        Index(value = ["id"], unique = true),
        Index(value = ["nickname"], unique = true),
    ]
)
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CmdEntityTags.ID)
    val id: Int= 0,

    @ColumnInfo(name = CmdEntityTags.ACCOUNT)
    var account: String?= "",

    @ColumnInfo(name = CmdEntityTags.NICKNAME)
    var nickName: String?= "",

    @ColumnInfo(name = CmdEntityTags.EMAIL)
    var email: String?= "",

    @ColumnInfo(name = CmdEntityTags.REG_DATE)
    var regDate: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.PROFILE_THUMBNAIL)
    var profileThumbnail: String?= "",

    @ColumnInfo(name = CmdEntityTags.F_TOKEN)
    var fToken: String?= ""

) {

    companion object {
        const val TABLE_USER = "user"
    }

}