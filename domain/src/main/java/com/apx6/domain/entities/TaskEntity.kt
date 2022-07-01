package com.apx6.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.domain.constants.CmdEntityTags
import com.apx6.domain.entities.TaskEntity.Companion.TABLE_TASK

@Entity(
    tableName = TABLE_TASK,
    foreignKeys = [
        ForeignKey(entity = UserEntity::class, parentColumns = ["id"], childColumns = ["uid"], onDelete = CASCADE),
        ForeignKey(entity = CategoryEntity::class, parentColumns = ["id"], childColumns = ["cid"], onDelete = CASCADE),
    ],
    indices = [Index(value = ["id"], unique = true)]
)
data class TaskEntity(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = CmdEntityTags.ID)
    var id: Long = 0,

    @field:ColumnInfo(name = CmdEntityTags.CID)
    var cid: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.UID)
    var uid: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.TITLE)
    var title: String?= "",

    @field:ColumnInfo(name = CmdEntityTags.MEMO)
    var memo: String?= "",

    @field:ColumnInfo(name = CmdEntityTags.START_DATE)
    var startDate: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.END_DATE)
    var endDate: Long?= 0L

) {

    companion object {
        const val TABLE_TASK = "task"
    }

}