package com.apx6.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.domain.constants.CmdEntityTags
import com.apx6.domain.entities.Sync.Companion.TABLE_SYNC

@Entity(
    tableName = TABLE_SYNC,
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["uid"],
            onDelete = CASCADE
        )
    ],
    indices = [Index(value = ["id"], unique = true)]
)
data class Sync(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CmdEntityTags.ID)
    var id: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.UID)
    val uid: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.SYNC_TIME)
    val syncTime: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.SYNC_RESULT)
    val syncResult: Boolean?= false

) {

    companion object {
        const val TABLE_SYNC = "sync"
    }

}