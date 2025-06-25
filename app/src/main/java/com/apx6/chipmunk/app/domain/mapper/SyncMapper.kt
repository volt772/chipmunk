package com.apx6.chipmunk.app.domain.mapper

import com.apx6.chipmunk.app.domain.dto.CmdSync
import com.apx6.chipmunk.app.domain.entities.Sync

interface SyncMapper {

    suspend fun syncToEntity(sync: CmdSync): Sync

}