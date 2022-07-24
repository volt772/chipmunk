package com.apx6.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apx6.domain.entities.Notification.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = CheckList::class,
            parentColumns = ["id"],
            childColumns = ["clId"],
            onDelete = CASCADE
        )
    ],
    indices = [Index(value = ["id"], unique = true)]
)
data class Notification(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var clId: Int,

    var period: Long
) {
    companion object {
        const val TABLE_NAME = "notification"
    }
}