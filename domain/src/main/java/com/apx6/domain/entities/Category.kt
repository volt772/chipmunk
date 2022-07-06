package com.apx6.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.domain.constants.CmdEntityTags
import com.apx6.domain.entities.Category.Companion.TABLE_CATEGORY

@Entity(
    tableName = TABLE_CATEGORY,
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
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CmdEntityTags.ID)
    var id: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.UID)
    val uid: Long?= 0L,

    @ColumnInfo(name = CmdEntityTags.NAME)
    val name: String?= ""
) {
    companion object {
        const val TABLE_CATEGORY = "category"
    }
}