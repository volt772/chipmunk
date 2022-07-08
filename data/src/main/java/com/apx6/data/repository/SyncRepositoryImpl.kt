package com.apx6.data.repository

import com.apx6.data.dao.SyncDao
import com.apx6.domain.dto.CmdSync
import com.apx6.domain.entities.Sync
import com.apx6.domain.mapper.SyncMapper
import com.apx6.domain.repository.BoundaryRepository
import com.apx6.domain.repository.Resource
import com.apx6.domain.repository.SyncRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SyncRepositoryImpl @Inject constructor(
    private val syncDao: SyncDao,
    private val syncMapper: SyncMapper
): SyncRepository {

    override suspend fun sync(sync: CmdSync): Flow<Resource<CmdSync?>> {
        return object: BoundaryRepository<CmdSync?, CmdSync>() {
            override suspend fun saveRemoteData(response: CmdSync) {
//                val entity = convertToEntity(response)
//                syncDao.insertOrUpdate(entity)
            }

            override fun fetchFromLocal(): Flow<CmdSync?> {
                return syncDao.getSync()
            }

        }.asFlow()
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