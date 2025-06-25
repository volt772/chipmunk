package com.apx6.chipmunk.app.data.repository

import com.apx6.chipmunk.app.data.dao.SyncDao
import com.apx6.chipmunk.app.domain.dto.CmdSync
import com.apx6.chipmunk.app.domain.entities.Sync
import com.apx6.chipmunk.app.domain.mapper.SyncMapper
import com.apx6.chipmunk.app.domain.repository.boundary.RemoteBoundaryRepository
import com.apx6.chipmunk.app.domain.repository.Resource
import com.apx6.chipmunk.app.domain.repository.SyncRepository
import com.apx6.chipmunk.app.domain.repository.boundary.LocalBoundaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SyncRepositoryImpl @Inject constructor(
    private val syncDao: SyncDao,
    private val syncMapper: SyncMapper
): SyncRepository {

    override suspend fun sync(sync: CmdSync): Flow<Resource<CmdSync?>> {
        return object: LocalBoundaryRepository<CmdSync?, CmdSync>() {
            override suspend fun postToLocal(obj: CmdSync) {

            }

            override fun fetchFromLocal(): Flow<CmdSync?> {
                return syncDao.getSync()
            }

        }.asFlow(sync)
    }

    override suspend fun postSync(sync: CmdSync) {
        val entity = convertToEntity(sync)
        syncDao.overwrite(entity)
    }

    override suspend fun getSync(): Flow<CmdSync?> {
        return syncDao.getSync()
    }

    override suspend fun delSync(): Boolean {
        return syncDao.delSync() > 0
    }

    private suspend fun convertToEntity(sync: CmdSync): Sync {
        return syncMapper.syncToEntity(sync)
    }

}