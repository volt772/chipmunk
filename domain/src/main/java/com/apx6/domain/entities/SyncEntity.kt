package com.apx6.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.domain.constants.CmdEntityTags
import com.apx6.domain.entities.SyncEntity.Companion.TABLE_SYNC

@Entity(
    tableName = TABLE_SYNC,
    foreignKeys = [ForeignKey(entity = UserEntity::class, parentColumns = ["id"], childColumns = ["uid"], onDelete = CASCADE)],
    indices = [Index(value = ["id"], unique = true)]
)
data class SyncEntity(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = CmdEntityTags.ID)
    var id: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.UID)
    var uid: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.SYNC_TIME)
    var syncTime: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.SYNC_RESULT)
    var syncResult: Boolean?= false

) {

    companion object {
        const val TABLE_SYNC = "sync"
    }

}