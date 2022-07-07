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
    val id: Int = 0,

    var cid: Int,

    var uid: Int,

    var title: String,

    var memo: String?= "",

    var startDate: Long,

    var endDate: Long
) {
    companion object {
        const val TABLE_TASK = "task"
    }
}