package com.apx6.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.domain.constants.CmdEntityTags
import com.apx6.domain.entities.Task.Companion.TABLE_TASK

@Entity(
    tableName = TABLE_TASK,
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["uid"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["cid"],
            onDelete = CASCADE
        ),
    ],
    indices = [Index(value = ["id"], unique = true)]
)
data class Task(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CmdEntityTags.ID)
    var id: Long = 0,

    @ColumnInfo(name = CmdEntityTags.CID)
    val cid: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.UID)
    val uid: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.TITLE)
    val title: String?= "",

    @ColumnInfo(name = CmdEntityTags.MEMO)
    val memo: String?= "",

    @ColumnInfo(name = CmdEntityTags.START_DATE)
    val startDate: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.END_DATE)
    val endDate: Long?= 0L

) {

    companion object {
        const val TABLE_TASK = "task"
    }

}