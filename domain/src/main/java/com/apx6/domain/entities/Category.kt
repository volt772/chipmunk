package com.apx6.domain.entities

import androidx.room.*
import androidx.room.ForeignKey.Companion.CASCADE
import com.apx6.domain.constants.CmdEntityTags
import com.apx6.domain.entities.Category.Companion.TABLE_CATEGORY

@Entity(
    tableName = TABLE_CATEGORY,
    foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["uid"], onDelete = CASCADE)],
    indices = [Index(value = ["id"], unique = true)]
)
data class Category(

    @field:PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = CmdEntityTags.ID)
    var id: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.UID)
    var uid: Long?= 0L,

    @field:ColumnInfo(name = CmdEntityTags.NAME)
    var name: String?= ""

) {

    companion object {
        const val TABLE_CATEGORY = "category"
    }

}