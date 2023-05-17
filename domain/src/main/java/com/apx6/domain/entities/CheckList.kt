package com.apx6.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.apx6.domain.entities.CheckList.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
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
data class CheckList(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var cid: Int,

    var uid: Int,

    var title: String,

    var memo: String?= "",

    var startDate: Long,

    var endDate: Long
) {
    companion object {
        const val TABLE_NAME = "checklist"
    }
}