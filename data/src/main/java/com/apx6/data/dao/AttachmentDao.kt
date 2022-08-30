package com.apx6.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.apx6.domain.dto.CmdAttachment
import com.apx6.domain.entities.Attachment
import kotlinx.coroutines.flow.Flow


@Dao
abstract class AttachmentDao : BaseDao<Attachment>() {

    /* ▼ TRANSACTION =====================================================================================================================*/

    /* ▼ SELECT ==========================================================================================================================*/
    @Query("SELECT * FROM ${Attachment.TABLE_NAME} WHERE clId = :clId")
    abstract fun getAttachments(clId: Int): Flow<List<CmdAttachment>>

    /* ▼ INSERT ==========================================================================================================================*/

    /* ▼ UPDATE ==========================================================================================================================*/

    /* ▼ DELETE ==========================================================================================================================*/
    @Query("DELETE FROM ${Attachment.TABLE_NAME} WHERE id = :id")
    abstract fun delAttachment(id: Int): Int

    /* ▼ TEST ONLY =======================================================================================================================*/

}