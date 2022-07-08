package com.apx6.domain.repository

import com.apx6.domain.dto.CmdSync
import kotlinx.coroutines.flow.Flow

interface SyncRepository {

    /* Combined*/
    suspend fun sync(sync: CmdSync): Flow<Resource<CmdSync?>>

    suspend fun postSync(sync: CmdSync)

    suspend fun getSync(): Flow<CmdSync?>

   suspend fun delSync(): Boolean

}