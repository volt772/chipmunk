package com.apx6.data.repository

import com.apx6.data.dao.AttachmentDao
import com.apx6.domain.dto.CmdAttachment
import com.apx6.domain.entities.Attachment
import com.apx6.domain.mapper.AttachMapper
import com.apx6.domain.repository.AttachRepository
import com.apx6.domain.repository.Resource
import com.apx6.domain.repository.boundary.LocalBoundaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AttachRepositoryImpl @Inject constructor(
    private val attachmentDao: AttachmentDao,
    private val attachMapper: AttachMapper
): AttachRepository {

    override suspend fun attachment(attachment: CmdAttachment, clId: Int): Flow<Resource<List<CmdAttachment>>> {
        return object: LocalBoundaryRepository<List<CmdAttachment>, CmdAttachment>() {
            override suspend fun postToLocal(obj: CmdAttachment) {
                val entity = convertToEntity(obj)
                attachmentDao.insertOrUpdate(entity)
            }

            override fun fetchFromLocal(): Flow<List<CmdAttachment>> {
                return attachmentDao.getAttachments(clId)
            }

        }.asFlow(attachment)
    }

    override suspend fun postAttachment(attachment: CmdAttachment) {
        val entity = convertToEntity(attachment)
        attachmentDao.insertOrUpdate(entity)
    }

    override suspend fun getAttachments(clId: Int): Flow<Resource<List<CmdAttachment>>> {
        val attachments = attachmentDao.getAttachments(clId)
        val result = attachments.map {
            Resource.Success(it)
        }

        return result
    }

    override suspend fun delAttachment(id: Int): Boolean {
        return attachmentDao.delAttachment(id) > 0
    }

    private suspend fun convertToEntity(attachment: CmdAttachment): Attachment {
        return attachMapper.attachToEntity(attachment)
    }

}