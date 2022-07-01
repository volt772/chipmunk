package com.apx6.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.domain.constants.CmdEntityTags
import com.apx6.domain.entities.NotificationEntity.Companion.TABLE_NOTIFICATION

@Entity(
    tableName = TABLE_NOTIFICATION,
    foreignKeys = [ForeignKey(entity = TaskEntity::class, parentColumns = ["id"], childColumns = ["tid"], onDelete = CASCADE)],
    indices = [Index(value = ["id"], unique = true)]
)
data class NotificationEntity(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = CmdEntityTags.ID)
    var id: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.TID)
    var tid: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.PERIOD)
    var period: Long?= 0L

) {

    companion object {
        const val TABLE_NOTIFICATION = "notification"
    }

}