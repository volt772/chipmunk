package com.apx6.domain.mapper

import com.apx6.domain.dto.CmdSync
import com.apx6.domain.entities.Sync

interface SyncMapper {

    suspend fun syncToEntity(sync: CmdSync): Sync

}