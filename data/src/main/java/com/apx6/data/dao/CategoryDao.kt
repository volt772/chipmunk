package com.apx6.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apx6.domain.entities.Category
import com.apx6.domain.entities.User
import kotlinx.coroutines.flow.Flow


@Dao
abstract class CategoryDao : BaseDao<Category>() {

    /* ▼ TRANSACTION =====================================================================================================================*/

    /* ▼ SELECT ==========================================================================================================================*/
    @Query("SELECT * FROM ${Category.TABLE_CATEGORY}")
    abstract fun getCategories(): Flow<List<Category>>

    @Query("SELECT * FROM ${Category.TABLE_CATEGORY} WHERE id = :id")
    abstract fun getCategory(id: Long): Category

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/

    /* ▼ TEST ONLY =======================================================================================================================*/

}