package com.apx6.chipmunk.app.data.mapper

import com.apx6.chipmunk.app.domain.dto.CmdAttachment
import com.apx6.chipmunk.app.domain.entities.Attachment
import com.apx6.chipmunk.app.domain.mapper.AttachMapper
import javax.inject.Inject

class AttachMapperImpl @Inject constructor(

): AttachMapper {

    override suspend fun attachToEntity(
        attachment: CmdAttachment
    ): Attachment {
        return Attachment(
            id = attachment.id,
            clId = attachment.clId,
            name = attachment.name,
            size = attachment.size,
            contentType = attachment.contentType,
            createdTime = attachment.createdTime
        )
    }
}