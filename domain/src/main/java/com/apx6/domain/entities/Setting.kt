package com.apx6.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.apx6.domain.entities.Setting.Companion.TABLE_NAME
import com.apx6.domain.entities.converter.CmdSettingTypeConverter

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["uid"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["id"], unique = true),
    ]
)
data class Setting(
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,

    var uid: Int,

    @TypeConverters(CmdSettingTypeConverter::class)
    var key: String,

    var value: String,

    var ext1: String?= "",

    var ext2: String?= "",

    var ext3: String?= "",

    var setDate: Long
) {
    companion object {
        const val TABLE_NAME = "setting"
    }
}