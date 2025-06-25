package com.apx6.chipmunk.app.data.repository

import com.apx6.chipmunk.app.data.dao.HistoryDao
import com.apx6.chipmunk.app.domain.dto.CmdHistory
import com.apx6.chipmunk.app.domain.entities.History
import com.apx6.chipmunk.app.domain.repository.HistoryRepository
import com.apx6.chipmunk.app.domain.repository.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyDao: HistoryDao,
): HistoryRepository {
    override suspend fun getKeywords(): Flow<Resource<List<CmdHistory>>> {
        return historyDao.getKeywords().map { Resource.Success(it) }
    }

    override suspend fun insert(keyword: String, regDate: Long): Boolean {
        val keywordId = historyDao.getKeywordId(keyword)

        return historyDao.insertOrUpdate(History(
            id = keywordId,
            keyword = keyword,
            regDate = regDate
        )) > 0
    }

    override suspend fun deleteKeyword(kid: Long) {
        historyDao.deleteKeyword(kid)
    }

    override suspend fun clearKeywords() {
        historyDao.clearKeywords()
    }

}