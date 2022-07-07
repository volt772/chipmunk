package com.apx6.domain.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apx6.domain.entities.User.Companion.TABLE_USER

@Entity(
    tableName = TABLE_USER,
    indices = [
        Index(value = ["id"], unique = true),
        Index(value = ["nickName"], unique = true),
    ]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,

    var account: String,

    var nickName: String,

    var email: String?= "",

    var regDate: Long,

    var profileThumbnail: String?= "",

    var fToken: String
) {
    companion object {
        const val TABLE_USER = "user"
    }
}