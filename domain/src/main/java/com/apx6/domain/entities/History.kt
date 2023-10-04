package com.apx6.domain.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apx6.domain.entities.History.Companion.TABLE_HISTORY

@Entity(
    tableName = TABLE_HISTORY,
    indices = [
        Index(value = ["id"], unique = true),
        Index(value = ["keyword"], unique = true)
      ],
)
data class History(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val keyword: String = "",

    val regDate: Long = 0L

) {
    companion object {
        const val TABLE_HISTORY = "history"
    }
}
