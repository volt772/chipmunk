package com.apx6.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.domain.constants.CmdEntityTags
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
    @ColumnInfo(name = CmdEntityTags.ID)
    var id: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.TID)
    val tid: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.PERIOD)
    val period: Long?= 0L

) {

    companion object {
        const val TABLE_NOTIFICATION = "notification"
    }

}