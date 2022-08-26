package com.apx6.data.mapper

import com.apx6.domain.dto.CmdAttachment
import com.apx6.domain.entities.Attachment
import com.apx6.domain.mapper.AttachMapper
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