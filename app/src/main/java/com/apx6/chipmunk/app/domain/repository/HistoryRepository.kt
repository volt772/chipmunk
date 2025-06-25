package com.apx6.chipmunk.app.domain.repository

import com.apx6.chipmunk.app.domain.dto.CmdHistory
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    suspend fun getKeywords(): Flow<Resource<List<CmdHistory>>>

    suspend fun insert(keyword: String, regDate: Long): Boolean

    suspend fun deleteKeyword(kid: Long)

    suspend fun clearKeywords()

}