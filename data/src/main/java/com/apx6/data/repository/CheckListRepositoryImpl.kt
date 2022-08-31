package com.apx6.data.repository

import com.apx6.data.dao.CheckListDao
import com.apx6.data.network.KakaoMapApiService
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.entities.CheckList
import com.apx6.domain.mapper.CheckListMapper
import com.apx6.domain.repository.CheckListRepository
import com.apx6.domain.repository.Resource
import com.apx6.domain.repository.boundary.LocalBoundaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckListRepositoryImpl @Inject constructor(
    private val api: KakaoMapApiService,
    private val checkListDao: CheckListDao,
    private val checkListMapper: CheckListMapper
): CheckListRepository {

    override suspend fun checklists(checkList: CmdCheckList, uid: Int): Flow<Resource<List<CmdCheckList>>> {
        return object: LocalBoundaryRepository<List<CmdCheckList>, CmdCheckList>() {
            override suspend fun postToLocal(obj: CmdCheckList) {
                val entity = convertToEntity(checkList)
                checkListDao.insertOrUpdate(entity)
            }

            override fun fetchFromLocal(): Flow<List<CmdCheckList>> {
                return checkListDao.getCheckLists(uid)
            }

        }.asFlow(checkList)
    }

    override suspend fun postCheckList(checkList: CmdCheckList) {
        val entity = convertToEntity(checkList)
        checkListDao.insertOrUpdate(entity)
    }

    override suspend fun getCheckLists(uid: Int): Flow<Resource<List<CmdCheckList>>> {
        val checkLists = checkListDao.getCheckLists(uid)
        val result = checkLists.map {
            Resource.Success(it)
        }

        return result
    }

    override suspend fun getCheckList(id: Int): Flow<CmdCheckList?> {
        return checkListDao.getCheckList(id)
    }

    override suspend fun patchCheckList(checkList: CmdCheckList): Boolean {
        val entity = convertToEntity(checkList)
        return checkListDao.update(entity) > 0
    }

    override suspend fun delCheckList(checkList: CmdCheckList): Boolean {
        val entity = convertToEntity(checkList)
        return checkListDao.delete(entity) > 0
    }

    override suspend fun getLocation(query: String) {
        val result = api.getLocationDocs(query)
        println("probe :: result : $result")
    }

    private suspend fun convertToEntity(checkList: CmdCheckList): CheckList {
        return checkListMapper.checkListToEntity(checkList)
    }
}