package com.apx6.chipmunk.app.domain.mapper

import com.apx6.chipmunk.app.domain.dto.CmdAttachment
import com.apx6.chipmunk.app.domain.entities.Attachment

interface AttachMapper {

    suspend fun attachToEntity(attachment: CmdAttachment): Attachment

}