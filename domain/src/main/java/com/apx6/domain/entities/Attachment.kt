package com.apx6.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.domain.constants.CmdEntityTags
import com.apx6.domain.entities.Attachment.Companion.TABLE_ATTACHMENT

@Entity(
    tableName = TABLE_ATTACHMENT,
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
data class Attachment(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CmdEntityTags.ID)
    var id: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.TID)
    val tid: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.NAME)
    val name: String?= "",

    @ColumnInfo(name = CmdEntityTags.SIZE)
    val size: Int?= 0,

    @ColumnInfo(name = CmdEntityTags.CONTENT_TYPE)
    val contentType: String?= "",

    @ColumnInfo(name = CmdEntityTags.CREATED_TIME)
    val createdTime: Long?= 0L

) {

    companion object {
        const val TABLE_ATTACHMENT = "attachment"
    }

}