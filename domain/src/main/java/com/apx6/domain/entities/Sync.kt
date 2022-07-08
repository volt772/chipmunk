package com.apx6.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apx6.domain.entities.Sync.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
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
    val id: Int = 0,

    var uid: Int,

    var syncTime: Long,

    var syncResult: Boolean
) {
    companion object {
        const val TABLE_NAME = "sync"
    }
}