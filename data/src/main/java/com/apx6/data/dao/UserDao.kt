package com.apx6.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apx6.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
abstract class UserDao : BaseDao<UserEntity>() {

    /* ▼ TRANSACTION =====================================================================================================================*/

    /* ▼ SELECT ==========================================================================================================================*/
    @Query("SELECT * FROM ${UserEntity.TABLE_USER}")
    abstract fun getUser(): Flow<UserEntity>

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/

    /* ▼ TEST ONLY =======================================================================================================================*/

}