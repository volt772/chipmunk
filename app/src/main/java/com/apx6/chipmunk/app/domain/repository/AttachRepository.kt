package com.apx6.chipmunk.app.domain.repository

import com.apx6.chipmunk.app.domain.dto.CmdAttachment
import com.apx6.chipmunk.app.domain.dto.CmdCategory
import kotlinx.coroutines.flow.Flow

interface AttachRepository {

    /* Combined*/
    suspend fun attachment(attachment: CmdAttachment, clId: Int): Flow<Resource<List<CmdAttachment>>>

    suspend fun postAttachment(attachment: CmdAttachment)

    suspend fun getAttachments(clId: Int): Flow<Resource<List<CmdAttachment>>>

    suspend fun delAttachment(id: Int): Boolean

}