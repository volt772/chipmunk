package com.apx6.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.entities.Category
import kotlinx.coroutines.flow.Flow


@Dao
abstract class CategoryDao : BaseDao<Category>() {

    /* ▼ TRANSACTION =====================================================================================================================*/

    /* ▼ SELECT ==========================================================================================================================*/
    @Query("SELECT * FROM ${Category.TABLE_NAME}")
    abstract fun getCategories(): Flow<List<CmdCategory>>

    @Query("SELECT * FROM ${Category.TABLE_NAME} WHERE id = :id")
    abstract fun getCategory(id: Long): Flow<CmdCategory?>

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/

    /* ▼ TEST ONLY =======================================================================================================================*/

}