package com.apx6.data.repository

import com.apx6.data.dao.CheckListDao
import com.apx6.data.network.KakaoMapApiService
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdCheckListWithCategory
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

    override suspend fun postCheckList(checkList: CmdCheckList): Boolean {
        val entity = convertToEntity(checkList)
        return checkListDao.insertOrUpdate(entity) > 0
    }

    override suspend fun getCheckLists(uid: Int, millis: Long, cid: Int?, query: String?): Flow<Resource<List<CmdCheckList>>> {
        val qString = query?.let { "%$query%" }

        val checkLists = checkListDao.getCheckListsFromMillis(uid, millis, cid, qString)
        val result = checkLists.map {
            Resource.Success(it)
        }

        return result
    }

    override suspend fun getCheckListsInCategory(uid: Int, cid: Int): Flow<List<CmdCheckList>> {
        return checkListDao.getCheckListInCategory(uid, cid)
    }

    override suspend fun getCheckList(id: Int): Flow<CmdCheckList?> {
        return checkListDao.getCheckList(id)
    }

    override suspend fun getCheckListWithCategory(id: Int): Flow<CmdCheckListWithCategory?> {
        return checkListDao.getCheckListWithCategory(id)
    }

    override suspend fun getCheckListCount(uid: Int): Flow<Resource<Int>> {
        val count = checkListDao.getCheckListCount(uid)
        val result = count.map {
            Resource.Success(it)
        }

        return result
    }

    override suspend fun getCheckListInWeek(tomorrowMillis: Long, weekMillis: Long): List<CmdCheckList> {
        return checkListDao.getCheckListsInWeek(tomorrowMillis, weekMillis)
    }

    override suspend fun patchCheckList(checkList: CmdCheckList): Boolean {
        val entity = convertToEntity(checkList)
        return checkListDao.update(entity) > 0
    }

    override suspend fun delCheckList(checkList: CmdCheckList): Boolean {
        val entity = convertToEntity(checkList)
        return checkListDao.delete(entity) > 0
    }


    private suspend fun convertToEntity(checkList: CmdCheckList): CheckList {
        return checkListMapper.checkListToEntity(checkList)
    }
}