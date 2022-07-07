package com.apx6.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apx6.domain.entities.Notification.Companion.TABLE_NOTIFICATION

@Entity(
    tableName = TABLE_NOTIFICATION,
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["tid"],
            onDelete = CASCADE
        )
    ],
    indices = [Index(value = ["id"], unique = true)]
)
data class Notification(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var tid: Int,

    var period: Long
) {
    companion object {
        const val TABLE_NOTIFICATION = "notification"
    }
}