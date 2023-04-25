package com.apx6.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.entities.CheckList
import kotlinx.coroutines.flow.Flow


@Dao
abstract class CheckListDao : BaseDao<CheckList>() {

    /* ▼ TRANSACTION =====================================================================================================================*/

    /* ▼ SELECT ==========================================================================================================================*/
    @Query("SELECT * FROM ${CheckList.TABLE_NAME} WHERE uid = :uid")
    abstract fun getCheckLists(uid: Int): Flow<List<CmdCheckList>>

    @Query("SELECT * FROM ${CheckList.TABLE_NAME} WHERE id = :id")
    abstract fun getCheckList(id: Int): Flow<CmdCheckList?>

    @Query("SELECT COUNT(*) FROM ${CheckList.TABLE_NAME} WHERE uid = :uid")
    abstract fun getCheckListCount(uid: Int): Flow<Int>

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/

    /* ▼ TEST ONLY =======================================================================================================================*/
    @Query("SELECT * FROM ${CheckList.TABLE_NAME} LIMIT 1")
    abstract fun testGetCheckList(): CmdCheckList?

    @Query("SELECT * FROM ${CheckList.TABLE_NAME} WHERE id = (SELECT max(id) FROM ${CheckList.TABLE_NAME})")
    abstract fun testGetLastCheckList(): CmdCheckList?

}