package com.apx6.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apx6.domain.dto.CmdTask
import com.apx6.domain.entities.Task
import kotlinx.coroutines.flow.Flow


@Dao
abstract class TaskDao : BaseDao<Task>() {

    /* ▼ TRANSACTION =====================================================================================================================*/

    /* ▼ SELECT ==========================================================================================================================*/
    @Query("SELECT * FROM ${Task.TABLE_NAME}")
    abstract fun getTasks(): Flow<List<CmdTask>>

    @Query("SELECT * FROM ${Task.TABLE_NAME} WHERE id = :id")
    abstract fun getTask(id: Int): Flow<CmdTask?>

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/

    /* ▼ TEST ONLY =======================================================================================================================*/
    @Query("SELECT * FROM ${Task.TABLE_NAME} LIMIT 1")
    abstract fun testGetTask(): CmdTask?

    @Query("SELECT * FROM ${Task.TABLE_NAME} WHERE id = (SELECT max(id) FROM ${Task.TABLE_NAME})")
    abstract fun testGetLastTask(): CmdTask?

}