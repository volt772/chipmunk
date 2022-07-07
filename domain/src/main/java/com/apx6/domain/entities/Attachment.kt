package com.apx6.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
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
    val id: Int = 0,

    var tid: Int,

    var name: String,

    var size: Int,

    var contentType: String,

    var createdTime: Long
) {
    companion object {
        const val TABLE_ATTACHMENT = "attachment"
    }
}