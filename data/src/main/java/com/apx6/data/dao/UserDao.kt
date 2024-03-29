package com.apx6.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.User
import kotlinx.coroutines.flow.Flow


@Dao
abstract class UserDao : BaseDao<User>() {

    /* ▼ TRANSACTION =====================================================================================================================*/

    /* ▼ SELECT ==========================================================================================================================*/
    @Query("SELECT * FROM ${User.TABLE_NAME}")
    abstract fun getUser(): Flow<CmdUser?>

    @Query("SELECT id FROM ${User.TABLE_NAME}")
    abstract fun getUserId(): Flow<Int?>

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/

    /* ▼ TEST ONLY =======================================================================================================================*/
    @Query("SELECT * FROM ${User.TABLE_NAME} LIMIT 1")
    abstract fun testGetUser(): CmdUser?
}