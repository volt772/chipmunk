package com.apx6.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apx6.domain.dto.CmdHistory
import com.apx6.domain.entities.History
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HistoryDao: BaseDao<History>() {

    /* ▼ SELECT ==========================================================================================================================*/
    @Query(
        value =
        """
        SELECT * FROM ${History.TABLE_HISTORY} 
        ORDER BY regDate DESC
        """
    )
    abstract fun getKeywords(): Flow<List<CmdHistory>>

    @Query(
        value =
        """
        SELECT id FROM ${History.TABLE_HISTORY} 
        WHERE keyword = :keyword
        """
    )
    abstract fun getKeywordId(keyword: String): Long

    /* ▼ INSERT ==========================================================================================================================*/
    /* ▼ DELETE ==========================================================================================================================*/
    @Query(
        value =
        """
        DELETE FROM ${History.TABLE_HISTORY} 
        WHERE id = :kid
        """
    )
    abstract fun deleteKeyword(kid: Long)

    @Query(
        value =
        """
        DELETE FROM ${History.TABLE_HISTORY} 
        """
    )
    abstract fun clearKeywords()
}
