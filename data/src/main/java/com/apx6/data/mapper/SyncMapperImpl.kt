package com.apx6.data.mapper

import com.apx6.domain.dto.CmdSync
import com.apx6.domain.entities.Sync
import com.apx6.domain.mapper.SyncMapper
import javax.inject.Inject

class SyncMapperImpl @Inject constructor(

): SyncMapper {
    override suspend fun syncToEntity(
        sync: CmdSync
    ): Sync {
        return Sync(
            id = sync.id,
            uid = sync.uid,
            syncTime = sync.syncTime,
            syncResult = sync.syncResult
        )
    }
}