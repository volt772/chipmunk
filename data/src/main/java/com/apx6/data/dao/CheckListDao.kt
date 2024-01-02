package com.apx6.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdCheckListWithCategory
import com.apx6.domain.entities.Category
import com.apx6.domain.entities.CheckList
import kotlinx.coroutines.flow.Flow


@Dao
abstract class CheckListDao : BaseDao<CheckList>() {

    /* ▼ TRANSACTION =====================================================================================================================*/

    /* ▼ SELECT ==========================================================================================================================*/
    @Query("SELECT * FROM ${CheckList.TABLE_NAME} WHERE uid = :uid ORDER BY exeDate DESC")
    abstract fun getCheckLists(uid: Int): Flow<List<CmdCheckList>>

    @Query(
        value =
        """
        SELECT ch.id, ch.cid, ch.uid, ch.title, ch.memo, ch.exeDate, ca.name AS checkListName
        FROM ${CheckList.TABLE_NAME} AS ch
        JOIN ${Category.TABLE_NAME} AS ca
        ON ch.cid = ca.id
        WHERE ch.uid = :uid 
        AND (:cid IS NULL OR ch.cid = :cid)
        AND (:query IS NULL OR (ch.title LIKE :query OR ch.memo LIKE :query))
        ORDER BY (CASE WHEN ch.exeDate = :millis THEN 0 ELSE 1 END), ch.exeDate DESC, ch.id DESC
        """
    )
    abstract fun getCheckListsFromMillis(uid: Int, millis: Long, cid: Int?= null, query: String?= null): Flow<List<CmdCheckList>>

    @Query("SELECT * FROM ${CheckList.TABLE_NAME} WHERE id = :id")
    abstract fun getCheckList(id: Int): Flow<CmdCheckList?>

    @Query("SELECT COUNT(*) FROM ${CheckList.TABLE_NAME} WHERE uid = :uid")
    abstract fun getCheckListCount(uid: Int): Flow<Int>

    @Query("SELECT * FROM ${CheckList.TABLE_NAME} WHERE uid = :uid AND cid = :cid")
    abstract fun getCheckListInCategory(uid: Int, cid: Int): Flow<List<CmdCheckList>>

    @Query(
        value =
        """
        SELECT cl.*, ca.name AS categoryName
        FROM ${CheckList.TABLE_NAME} AS cl 
        LEFT JOIN ${Category.TABLE_NAME} AS ca 
        ON cl.cid = ca.id WHERE cl.id = :clId
        """
    )
    abstract fun getCheckListWithCategory(clId: Int): Flow<CmdCheckListWithCategory?>

    @Query(
        value =
        """
        SELECT *
        FROM ${CheckList.TABLE_NAME}
        WHERE exeDate BETWEEN :tomorrowMillis AND :weekMillis
        """
    )
    abstract fun getCheckListsInWeek(tomorrowMillis: Long, weekMillis: Long): List<CmdCheckList>

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/
    @Query(
        value =
        """
        DELETE FROM ${CheckList.TABLE_NAME}
        WHERE id = :id
        """
    )
    abstract fun delCheckListsById(id: Int): Int

    /* ▼ TEST ONLY =======================================================================================================================*/
    @Query("SELECT * FROM ${CheckList.TABLE_NAME} LIMIT 1")
    abstract fun testGetCheckList(): CmdCheckList?

    @Query("SELECT * FROM ${CheckList.TABLE_NAME} WHERE id = (SELECT max(id) FROM ${CheckList.TABLE_NAME})")
    abstract fun testGetLastCheckList(): CmdCheckList?

}