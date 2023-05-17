package com.apx6.domain.mapper

import com.apx6.domain.dto.CmdAttachment
import com.apx6.domain.entities.Attachment

interface AttachMapper {

    suspend fun attachToEntity(attachment: CmdAttachment): Attachment

}