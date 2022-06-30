package com.apx6.chipmunk.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.chipmunk.domain.constants.CmdEntityTags
import com.apx6.chipmunk.domain.entities.AttachmentEntity.Companion.TABLE_ATTACHMENT

@Entity(
    tableName = TABLE_ATTACHMENT,
    foreignKeys = [ForeignKey(entity = TaskEntity::class, parentColumns = ["id"], childColumns = ["tid"], onDelete = CASCADE)],
    indices = [Index(value = ["id"], unique = true)]
)
data class AttachmentEntity(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = CmdEntityTags.ID)
    var id: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.TID)
    var tid: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.NAME)
    var name: String?= "",

    @field:ColumnInfo(name = CmdEntityTags.SIZE)
    var size: Int?= 0,

    @field:ColumnInfo(name = CmdEntityTags.CONTENT_TYPE)
    var contentType: String?= "",

    @field:ColumnInfo(name = CmdEntityTags.CREATED_TIME)
    var createdTime: Long?= 0L

) {

    companion object {
        const val TABLE_ATTACHMENT = "attachment"
    }

}