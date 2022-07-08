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
    abstract fun getCategory(id: Int): Flow<CmdCategory?>

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/

    /* ▼ TEST ONLY =======================================================================================================================*/
    @Query("SELECT * FROM ${Category.TABLE_NAME} LIMIT 1")
    abstract fun testGetCategory(): CmdCategory?

    @Query("SELECT * FROM ${Category.TABLE_NAME} WHERE id = (SELECT max(id) FROM ${Category.TABLE_NAME})")
    abstract fun testGetLastCategory(): CmdCategory?

}